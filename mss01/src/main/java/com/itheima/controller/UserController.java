package com.itheima.controller;

import com.itheima.domain.PageBean;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @RequestMapping("/login")
    public String login(User user,Integer ck, HttpSession session ,HttpServletResponse response) {

        System.out.println("登录");
        User u = service.login(user);
        if (u == null) {
            return "loginError";
        }
        //ck有值且为1 则表示记住,否则没记住
        //创建cookie
        Cookie cookieUsername = new Cookie("username", user.getUsername());
        Cookie cookiePassword = new Cookie("password", user.getPassword());
        if (ck != null && ck == 1) {
            cookieUsername.setMaxAge(7 * 26 * 60 * 60);
            cookiePassword.setMaxAge(7 * 24 * 60 * 60);
        } else {
            cookiePassword.setMaxAge(0);
            cookieUsername.setMaxAge(0);
        }
        //设置cookie存储路径
        cookieUsername.setPath("/");
        cookiePassword.setPath("/");
        response.addCookie(cookieUsername);
        response.addCookie(cookiePassword);

        System.out.println("................................");
        session.setAttribute("user",user);
        System.out.println(session.getAttribute("user")==null+"第一次验证");

        return "index1";
    }
    @RequestMapping("/findAll")
    public String findAll(Model model) {
        System.out.println("查询所有");
        List<User> list=  service.findAll();
        model.addAttribute("userList", list);
        return "list";

    }
    /**
     * 新增联系人
     * */
    @RequestMapping("add")
    public ModelAndView add(User user) {
        System.out.println("新增联系人");
        service.add(user);

        return new ModelAndView("redirect:/user/findAll");
    }

    /**
     * 删除联系人
     * */
    @RequestMapping("delete")
    public ModelAndView delete(Integer id) {
        System.out.println("删除联系人");
        service.delete(id);
        return new ModelAndView("redirect:/user/findAll");
    }

    /**
     * 删除选中
     * */
    @RequestMapping("/deleteSelect")
    public String  deleteSelect(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("deleteSelect执行");
        String [] uids = request.getParameterValues("uid");
        for (String uid : uids) {
            service.delete(Integer.parseInt(uid));
        }
        System.out.println(uids);
        return "redirect:/user/findAll";
    }


    /**
     * 修改用户,回显数据,
     * */
    @RequestMapping("/findUser")
    public void findUser(Integer id, HttpServletRequest request, HttpServletResponse response)throws Exception {
        System.out.println("修改用户");

        User user= service.findUser(id);

        request.setAttribute("user",user);

        request.getRequestDispatcher("/WEB-INF/pages/update.jsp").forward(request,response);

    }

    /**
     * 分页查询
     * */
    @RequestMapping("/finUserByPage")
    public String findUserByPage(Model model,
            @RequestParam(name = "currentPage",required = false,defaultValue ="1") int currentPage,
            @RequestParam(name = "rows",required = false,defaultValue = "5") int  rows) {
        System.out.println("分页查询");
        //获取当前页


        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setRows(rows);
        //获取每页显示条数

        //获取总信息数
        Integer totalCount= service.findTotalCount();
        pageBean.setTotalCount(totalCount);
        System.out.println(totalCount);

        //获取每页的用户信息
        List<User> list= service.findUserList(pageBean);
        model.addAttribute("userList",list);
        model.addAttribute("pageBean", pageBean);
        return "list";
    }


}
