<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2017/7/14
  Time: 上午9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>CDAR法院类案推荐系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <meta name="keywords" content="Agrico Farm Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- bootstrap-css -->
    <link href="../css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <!--// bootstrap-css -->
    <!-- css -->
    <link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="../css/searchForm.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="../css/signin_out.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="../css/user.css" type="text/css" media="all"/>
    <!--// css -->
    <!-- font-awesome icons -->
    <link href="../css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <!-- font -->
    <link href="//fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
    <link href="//fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i"
          rel="stylesheet">
    <!-- //font -->
    <script src="../js/jquery-1.11.1.min.js"></script>
    <script src="../js/bootstrap.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);
            });
        });
    </script>
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <![endif]-->
</head>
<body>
<!-- banner -->
<div class="banner">
    <div class="header">
        <div class="sign_in_logo">
            <a href="#log" title="登录" onclick="showLoginView()"><img src="../images/login.png" alt="" width="25px" height="25px"></a>
            |
            <a href="#sign" title="注册" onclick="showSigninView()"><img src="../images/signup.png" alt="" width="25px"
                                                                       height="25px"></a>
        </div>
        <div class="container">
            <div class="header-left">
                <div class="w3layouts-logo">
                    <h1>
                        <a href="index.jsp">CDAR<span>法院类案推荐系统</span></a>
                    </h1>
                </div>
            </div>
        </div>
    </div>
    <div class="header-bottom">
        <div class="container" style="margin-top: 0">
            <div class="top-nav">
                <nav class="navbar navbar-default">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                                data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li><a class="active" href="index.jsp">首页</a></li>
                            <li><a href="views/civilCase.jsp">民事案件</a></li>
                            <li><a href="#">查询类案</a></li>
                            <li class="dropdown">
                                <a href="codes.html" class="dropdown-toggle" data-hover="Pages" data-toggle="dropdown">About
                                    <!-- <b class="caret"></b> -->
                                </a>
                                <!-- <ul class="dropdown-menu">
                                    <li><a href="#">Icons</a></li>
                                    <li><a href="#">Short Codes</a></li>
                                </ul> -->
                            </li>
                            <li><a href="#">Contact</a></li>
                        </ul>
                        <div class="clearfix"></div>
                    </div>
                </nav>
            </div>
            <!-- w3-banner -->
            <div class="w3-banner">
                <!-- <div class="w3layouts-border"> </div>
                <h2>Lorem ipsum <span>dolor</span></h2>
                <p>Nunc vel efficitur sem, sed convallis mi. Praesent <span>sollicitudin aliquam eros</span></p>
                <div class="w3layouts-border agileits-border"> </div> -->
                <div class="search" id="searchView">
                    <i> </i>
                    <div class="s-bar">
                        <form>
                            <input type="text" value="请输入案例关键字"
                                   onfocus="if (this.value == '请输入案例关键字'){this.value = '';}"
                                   onblur="if (this.value == '') {this.value = '请输入案例关键字';}" id="textField">
                            <input type="submit" value="搜索" onclick="search()"/>
                        </form>
                    </div>
                </div>
                <div class="login" id="loginView" style="display: none">
                    <div class="l-bar">
                        <button onclick="closeLoginView()"></button>
                        <p style="margin-left: 0%; margin-top: 0%;">账户：</p>
                        <input type="text" style="margin-left: 0%; text-align: left; display: inline-block;"
                               id="logInUsername">
                        <br>
                        <p style="margin-left: 0%; margin-top: 10%;">密码：</p>
                        <input type="password" style="margin-left: 0%; text-align: left; display: inline-block;"
                               id="logInPassword">
                        <br>
                        <input type="submit" value="登陆" onclick="logIn()">
                    </div>
                </div>
                <div class="login" id="signinView" style="display: none">
                    <div class="l-bar">
                        <button onclick="closeSigninView()"></button>
                        <p style="margin-left: 0%; margin-top: 0%;">账户：</p>
                        <input type="text" style="margin-left: 0%; text-align: left; display: inline-block;"
                               id="signInUsername">
                        <br>
                        <p style="margin-left: 0%; margin-top: 10%;">密码：</p>
                        <input type="text" style="margin-left: 0%; text-align: left; display: none;" id="signInText">
                        <input type="password" style="margin-left: 0%; text-align: left; display: inline-block;"
                               id="signInPassword">
                        <button onclick="showPassword()"
                                style="margin-left: 0%; margin-top: 10%; background-image: url(images/closeEye.png)"
                                id="eyeButton"></button>
                        <br>
                        <input type="submit" value="注册" onclick="signIn()">
                    </div>
                </div>
                <div id="uploadView" style="display: none">
                    <input type="file" id="file" name="file" value="选择文件"/>
                    <input type="button" value="上传" onclick="upload();"/>
                </div>
                <!-- //w3-banner -->
            </div>
        </div>
    </div>
</div>
<!-- //banner -->

<!-- footer -->
<footer style="padding-top: 30px">
    <div class="container">
        <div class="w3-agileits-footer-grids">
            <div class="col-md-3 w3ls-footer-grid">
                <h4>关于 <span>CDAR</span></h4>
                <div class="wthree-footer-info">
                    <p>CDAR是通过采取关键字过滤的算法抽取司法案例关键信息，进行自然语义分析，
                        从而提供与待审判案件类似的案例推荐的信息系统.</p>
                    <ul>
                        <li><i class="fa fa-map-marker" aria-hidden="true"></i> : 江苏省南京市栖霞区仙林大道163号</li>
                        <li><i class="fa fa-phone" aria-hidden="true"></i> : +1 234 567 8910</li>
                        <li><i class="fa fa-envelope" aria-hidden="true"></i> : <a
                                href="#">winetasters@smail.nju.edu.cn</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-md-3 w3ls-footer-grid">
                <h4>最新 <span>案例</span></h4>
                <div class="agile-post-grids">
                    <div class="agile-post-grid">
                        <div class="col-md-5 agile-post-left">
                            <a href="single.html">
                                <img src="../images/img1.jpg" alt=""/>
                            </a>
                        </div>
                        <div class="col-md-7 agile-post-right">
                            <h5><a href="single.html">Nulla at arcu eu justo blandit.</a></h5>
                            <p><i class="fa fa-calendar-o" aria-hidden="true"></i> 03 Jan 2017</p>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="agile-post-grid">
                        <div class="col-md-5 agile-post-left">
                            <a href="single.html">
                                <img src="../images/img2.jpg" alt=""/>
                            </a>
                        </div>
                        <div class="col-md-7 agile-post-right">
                            <h5><a href="single.html">Nulla at arcu eu justo blandit.</a></h5>
                            <p><i class="fa fa-calendar-o" aria-hidden="true"></i> 24 Jan 2017</p>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 w3ls-footer-grid">
                <h4>本站 <span>导航</span></h4>
                <ul>
                    <li><a href="about.html">民事案件</a></li>
                    <li><a href="gallery.html">查询类案</a></li>
                    <li><a href="icons.html">Icons</a></li>
                </ul>
            </div>
            <div class="col-md-3 w3ls-footer-grid">
                <h4>相关 <span>网站</span></h4>
                <ul>
                    <li><a href="http://wenshu.court.gov.cn/">中国裁判文书网</a></li>
                    <li><a href="http://law1.law-star.com/">法律之星官网-法律检索</a></li>
                    <li><a href="http://www.court.gov.cn/index.html">中华人民共和国最高人民法院</a></li>
                    <li><a href="http://www.chinacourt.org/index.shtml">中国法院网</a></li>
                    <li><a href="http://www.gov.cn/index.htm">中国政府网_中央人民政府</a></li>
                </ul>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</footer>
<!-- //footer -->
<!-- copyright -->
<div class="agileits-w3layouts">
    <div class="container">
        <p>© 2017 CDAR. All rights reserved | Design by <a href="#">Wine Tasters</a></p>
    </div>
</div>
<!-- //copyright -->
<script src="../js/responsiveslides.min.js"></script>
<script src="../js/SmoothScroll.min.js"></script>
<script type="text/javascript" src="../js/move-top.js"></script>
<script type="text/javascript" src="../js/easing.js"></script>
<script type="text/javascript" src="../js/user.js"></script>
<script type="text/javascript" src="../js/search.js"></script>
<script type="text/javascript" src="../js/ajaxfileupload.js"></script>
<!-- here stars scrolling icon -->
<script type="text/javascript">
    $(document).ready(function () {

        var defaults = {
            containerID: 'toTop', // fading element id
            containerHoverID: 'toTopHover', // fading element hover id
            scrollSpeed: 1200,
            easingType: 'linear'
        };


        $().UItoTop({easingType: 'easeOutQuart'});

    });
</script>
<!-- //here ends scrolling icon -->
</body>
</html>
