package com.renbaojia.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;
import com.renbaojia.blog.model.User;
import com.renbaojia.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Created with IntelliJ IDEA.
 * @Author renbaojia
 * @Date: 2018-11-25 04:27
 * @version: 1.0
 * @Description: user操作控制器
 */
@Controller
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/testSql")
    public boolean login(User user) {
        return userService.login(user);

    }

    @ResponseBody
    @RequestMapping("/allUser")
    public PageInfo<User> allUser(@RequestParam Integer pageSize) {
        PageHelper.startPage(pageSize, 2);
        List<User> all = userService.all(pageSize);
        return new PageInfo<>(all);

    }

    @GetMapping("/afterLogin.html")
    public void afterLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String imgUrl;
        PrintWriter out = response.getWriter();
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            String accessToken = null,
                    openID = null;
            long tokenExpireIn = 0L;
            if (accessTokenObj.getAccessToken().equals("")) {
//                我们的网站被CSRF攻击了或者用户取消了授权
//                做一些数据统计工作
                System.out.println(accessTokenObj.getAccessToken());
            } else {
                accessToken = accessTokenObj.getAccessToken();
                tokenExpireIn = accessTokenObj.getExpireIn();
                request.getSession().setAttribute("demo_access_token", accessToken);
                request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));
                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj = new OpenID(accessToken);
                System.out.println(accessToken);
                openID = openIDObj.getUserOpenID();
                System.out.println(openID);
                /*session.setAttribute("openID", openID);
                if (userService.checkByOpenID(openID) && accessToken != null) {
                    User user = userService.selectByOpenID(openID);
                    Subject currentUser = SecurityUtils.getSubject();
                    UsernamePasswordToken token = new UsernamePasswordToken(user.getPhone(), user.getPassword());
                    try {
                        // 执行登录.
                        token.setRememberMe(true);
                        currentUser.login(token);
                    }
                    // ... catch more exceptions here (maybe custom ones specific to your application?
                    // 所有认证时异常的父类.
                    catch (Exception ae) {
                        //unexpected condition?  error?
                        System.out.println("登录失败: " + ae.getMessage());
                        // return null;

                    }
                    //获取SessionId;
                    String attribute = (String) session.getAttribute("SESSION_USERNAME");
                    System.out.println("登录之后获取的：" + attribute);
                    infoHandler().sendMessageToUser(attribute, new TextMessage(user.getImage().toString()));
                    infoHandler().sendMessageToUser(attribute, new TextMessage(user.getUsername().toString()));
                    session.setAttribute("userInfo", user);
                    System.out.println("登录成功: ");
                    //  return "index";
                } else {
                    session.setAttribute("OpenID", openID);
                    String attribute = (String) session.getAttribute("SESSION_USERNAME");
                    infoHandler().sendMessageToUser(attribute, new TextMessage("bind"));
                    System.out.println("欢迎你，代号为 " + openID + " 的用户!");
                    // request.getSession().setAttribute("demo_openid", openID);
                    System.out.println("<a href=" + "/shuoshuoDemo.html" + " target=\"_blank\">去看看发表说说的demo吧</a>");
                    // 利用获取到的accessToken 去获取当前用户的openid --------- end
                    System.out.println("<p> start -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- start </p>");
                    UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                    UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                    out.println("<br/>");
                    if (userInfoBean.getRet() == 0) {
                        System.out.println(userInfoBean.getNickname() + "<br/>");
                        System.out.println(userInfoBean.getGender() + "<br/>");
                        session.setAttribute("gender", userInfoBean.getGender());
                        session.setAttribute("imageUrl", userInfoBean.getAvatar().getAvatarURL100().toString());
                        session.setAttribute("pickName", userInfoBean.getNickname());
                        // System. out.println("黄钻等级： " + userInfoBean.getLevel() + "<br/>");
                        // System. out.println("会员 : " + userInfoBean.isVip() + "<br/>");
                        // System.out.println("黄钻会员： " + userInfoBean.isYellowYearVip() + "<br/>");
                        // System.out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL30() + "/><br/>");
                        // System.out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL50() + "/><br/>");
                        // System. out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL100() + "/><br/>");

                    } else {
                        //  System.out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg());
                    }
                    // System. out.println("<p> end -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- end </p>");
                    // System.out.println("<p> start ----------------------------------- 验证当前用户是否为认证空间的粉丝------------------------------------------------ start <p>");
                    PageFans pageFansObj = new PageFans(accessToken, openID);
                    PageFansBean pageFansBean = pageFansObj.checkPageFans("97700000");
                    if (pageFansBean.getRet() == 0) {
                        out.println("<p>验证您" + (pageFansBean.isFans() ? "是" : "不是") + "QQ空间97700000官方认证空间的粉丝</p>");
                    } else {
                        //    out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + pageFansBean.getMsg());
                    }
                    //   System.out.println("<p> end ----------------------------------- 验证当前用户是否为认证空间的粉丝------------------------------------------------ end <p>");


                    //System. out.println("<p> start -----------------------------------利用获取到的accessToken,openid 去获取用户在微博的昵称等信息 ---------------------------- start </p>");
                    com.qq.connect.api.weibo.UserInfo weiboUserInfo = new com.qq.connect.api.weibo.UserInfo(accessToken, openID);
                    com.qq.connect.javabeans.weibo.UserInfoBean weiboUserInfoBean = weiboUserInfo.getUserInfo();
                    if (weiboUserInfoBean.getRet() == 0) {
                        imgUrl = weiboUserInfoBean.getAvatar().getAvatarURL100();
                        //获取用户的微博头像----------------------start
                        //   System.  out.println("<image src=" + weiboUserInfoBean.getAvatar().getAvatarURL30() + "/><br/>");
                        //   System. out.println("<image src=" + weiboUserInfoBean.getAvatar().getAvatarURL50() + "/><br/>");
                        //  System. out.println("<image src=" + weiboUserInfoBean.getAvatar().getAvatarURL100() + "/><br/>");
                        //   session.setAttribute("detialsimage", weiboUserInfoBean.getAvatar().getAvatarURL50());
                        //获取用户的微博头像 ---------------------end
                        //获取用户的生日信息 --------------------start
                        //  System.  out.println("<p>尊敬的用户，你的生日是： " + weiboUserInfoBean.getBirthday().getYear()    + "年" + weiboUserInfoBean.getBirthday().getMonth() + "月" +
                        //                        weiboUserInfoBean.getBirthday().getDay() + "日");
                        //获取用户的生日信息 --------------------end
                        StringBuffer sb = new StringBuffer();
                        sb.append("<p>所在地:" + weiboUserInfoBean.getCountryCode() + "-" + weiboUserInfoBean.getProvinceCode() + "-" + weiboUserInfoBean.getCityCode()
                                + weiboUserInfoBean.getLocation());

                        //获取用户的公司信息---------------------------start
                        ArrayList<Company> companies = weiboUserInfoBean.getCompanies();
                        if (companies.size() > 0) {
                            //有公司信息
                            for (int i = 0, j = companies.size(); i < j; i++) {
                                sb.append("<p>曾服役过的公司：公司ID-" + companies.get(i).getID() + " 名称-" +
                                        companies.get(i).getCompanyName() + " 部门名称-" + companies.get(i).getDepartmentName() + " 开始工作年-" +
                                        companies.get(i).getBeginYear() + " 结束工作年-" + companies.get(i).getEndYear());
                            }
                        } else {
                            //没有公司信息
                        }
                        //获取用户的公司信息---------------------------end

                        System.out.println(sb.toString());

                    } else {
                        System.out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + weiboUserInfoBean.getMsg());
                    }

                    System.out.println("<p> end -----------------------------------利用获取到的accessToken,openid 去获取用户在微博的昵称等信息 ---------------------------- end </p>");
*/
                /**
                 * 添加shiro认证
                 */
                  /*  User user = new User();
                    user.setUsername(userInfoBean.getNickname());
                    user.setPassword("1");
                    session.setAttribute("userinfo", user);*/


                //return "index";
            
        }
    } catch(
    QQConnectException e)

    {
        e.printStackTrace();
    }
    //   return "index";
}
}
