<%--
  Created by IntelliJ IDEA.
  User: Observer
  Date: 2017/7/19
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>CDAR-查询类案</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- bootstrap-css -->
    <link href="../css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
    <!--// bootstrap-css -->
    <!-- css -->
    <link rel="stylesheet" href="../css/style.css" type="text/css" media="all" />
    <link rel="stylesheet" href="../css/searchForm2.css" type="text/css" media="all" />
    <link rel="stylesheet" href="../css/signin_out.css" type="text/css" media="all" />
    <link rel="stylesheet" href="../css/user.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="../css/accordion.css" type="text/css" media="all" />
    <link rel="stylesheet" href="../css/analyzeDoc.css" type="text/css" media="all" />
    <link rel="stylesheet" href="../css/button.css" type="text/css" media="all" />
    <link rel="stylesheet" href="../css/upload.css" type="text/css" media="all" />
    <!--// css -->
    <!-- font-awesome icons -->
    <link href="../css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <!-- font -->
    <link href="//fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
    <link href="//fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
    <!-- //font -->
    <script src="../js/jquery-1.11.1.min.js"></script>
    <script src="../js/jquery.jqpagination.js"></script>
    <script src="../js/bootstrap.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function($) {
            $(".scroll").click(function(event){
                event.preventDefault();
                $('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
            });

            checkCookie();
            up();
        });

    </script>
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <![endif]-->
</head>
<body style="background: url(../images/bg_1.jpg) no-repeat fixed; background-size: 100%">
<!-- banner -->
<div class="banner">
    <div class="header">
        <div id="preLog" class="sign_in_logo">
            <a href="#" title="登录" onclick="showLoginView()"><img src="../images/login.png" alt="" width="25px" height="25px"></a>
            |
            <a href="#" title="注册" onclick="showSigninView()"><img src="../images/signup.png" alt="" width="25px" height="25px"></a>
        </div>
        <div id="afterLog" class="sign_in_logo" style="display: none">
            <ul class="nav nav-pills">
                <%--<li class="active"><a href="#">Home</a></li>--%>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <span class="glyphicon glyphicon-user"></span>
                        <span id="userNameLabel">asd</span>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" style="min-width: 100px;">
                        <li><a href="#" onclick="">用户中心</a></li>
                        <li><a href="#" onclick="quitLog()">退出登录</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="container">
            <div class="header-left">
                <div class="w3layouts-logo">
                    <h1>
                        <a href="../index.jsp">CDAR<span>法院类案推荐系统</span></a>
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
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li><a href="../index.jsp">首页</a></li>
                            <li><a href="civilCase.jsp">民事案件</a></li>
                            <li><a href="analyzeDoc.jsp" class="active">查询类案</a></li>
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
                        <div class="clearfix"> </div>
                    </div>
                </nav>
            </div>
            <!-- agileits-top-heading -->
            <div class="agileits-top-heading">
                <div class="search"  id="searchView">
                    <i> </i>
                    <div class="s-bar">
                        <input type="text" value="请输入案例关键字"
                               onfocus="if (this.value == '请输入案例关键字'){this.value = '';}"
                               onblur="if (this.value == '') {this.value = '请输入案例关键字';}" id="textField">
                        <input type="submit" value="搜索" onclick="search()"/>
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
                        <input type="submit" value="登陆" onclick="log()">
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
                                style="margin-left: 0%; margin-top: 10%; background-image: url(/images/closeEye.png)"
                                id="eyeButton"></button>
                        <br>
                        <input type="submit" value="注册" onclick="signIn()">
                    </div>
                </div>
                <div id="uploadView" style="display: none">
                    <input type="file" id="file" name="file" value="选择文件"/>
                    <input type="button" value="上传" onclick="upload();"/>
                </div>
            </div>
            <!-- //agileits-top-heading -->
        </div>
    </div>
</div>
<!-- //banner -->
<!-- cases -->
<!-- main-textgrids -->
<div class="main-textgrids">
    <div class="container">
        <div class="statements">

            <!--上传XML文件-->
            <div class="upload effect">
                <form id="upload" method="post" action="/manageAction/upload" enctype="multipart/form-data">
                    <div id="drop">
                        <a>上传文件</a>
                        <input type="file" id="file" name="file" value="选择文件"/>
                    </div>
                    <ul id="fileArea">
                        <!-- The file uploads will be shown here -->
                    </ul>
                </form>
                <!-- JavaScript Includes -->
                <script src="../js/jquery.knob.js"></script>
                <!-- JavaScript Includes -->

                <!-- jQuery File Upload Dependencies -->
                <script src="../js/jquery.ui.widget.js"></script>
                <script src="../js/jquery.iframe-transport.js"></script>
                <script src="../js/jquery.fileupload.js"></script>
                <script src="../js/ajaxfileupload.js"></script>
                <!-- jQuery File Upload Dependencies -->

                <!-- Main JavaScript file -->
                <script src="../js/script.js"></script>
                <!-- Main JavaScript file -->

                <div class="button">
                    <div class="cancel"><a href="#">取消</a></div>
                    <div class="done"><a href="#" id="confirm">确认</a></div>
                    <div class="clear"> </div>
                </div>
            </div>
            <!--文书详情（上传文件后显示？）-->
            <div id="resultPage">
                <!--上传的案例全文-->
                <div class="document effect">
                    <h4 class="title" id="caseTitle">
                        湖南省耒阳市人民法院
                        民事判决书
                    </h4>
                    <div class="caseNumber" id="caseNum">
                        （2006）港民初字第1025号
                    </div>
                    <!--原文正文-->
                    <div class="origin_document" id="caseContent">
                        原告：谢某某，男。
                        委托代理人：陈某某，男。
                        被告：谷某，男。
                        委托代理人：曹某某，男。
                        原告谢某某与被告谷某提供劳务者受害责任纠纷一案，本院于2016年6月1日立案后，依法适用普通程序，公开开庭进行了审理。原告谢某某及其委托代理人陈某某，被告谷某及其委托代理人曹某某，本案现已审理终结。
                        原告谢某某向本院提出诉讼请求：1、判令被告赔偿原告受伤后各项经济损失331018.08元；2、判令被告谷某承担本案诉讼费用。事实和理由：2015年8月份，被告将自己住房的双飞粉粉刷工程以每平方米7元的价格交给原告进行粉刷。2015年8月25日上午，当原告粉刷至被告家中4楼楼梯间顶棚时，因由被告所提供并搭建的木架断裂，导致原告从木架上摔到楼梯间而受伤。在原告受伤后，被告立即将原告送至常宁市中医院进行救治并支付了在该医院的全部医疗
                        被告谷某辩称：与原告之间并未构成劳务关系,双方的法律关系系加工承揽关系。原告受伤亦完全是因为其自身的原因导致的，被告并无过错。但被告已向原告支付了医疗费27800元。现原告请求被告赔偿其损失，无事实依据和法律依据。另原告八级伤残的鉴定系伪造而来。
                        当事人围绕诉讼请求依法提交了证据，本院组织当事人进行了证据交换和质证。对于当事人有争议的证据，本院作如下认定：
                        1、原告提供的医疗住院结算单、门诊收据、住院明细清单和病历，用以证明原告受伤后分别在常宁市中医院、宜章县中医医院、耒阳市人民医院和衡阳市南华大学附属第一医院进行治疗，共计住院28天，花费医疗费用11906.36元的事实。被告对该证据关联性及合法性均持有异议，认为在证据中体现的部分用药并不适用于原告的治疗，且认为医疗费用过高。本院认为，被告对其所持的异议都未能提供证据予以支持，也未向本院提出对被告所用药品进行鉴定的请求，而原告提供的证据来源合法、真实可信，故本院对该证据予以认定。
                        2、原告提供的衡阳华夏司法鉴定所司法鉴定意见书、鉴定费收费票据和鉴定照相收据，用以证明原告受伤后，其伤残程度经司法鉴定评定为8级、误工期为150天、护理期为120天、营养期为120天，另用去鉴定费700元，鉴定照相费60元。被告对该证据的真实性无异议，但对其合法性和关联性均有异议，认为伤残评定的残级过高，后续治疗的情况依据不足。本院认为，原告提供的司法鉴定意见书系具有资质的鉴定机构出具的，程序合法，事实清楚，能够客观反映案件的事实。被告对于其提出的异议未能举出证据予以支持，
                        亦未向本院申请对原告的伤残程度进行重新鉴定，故本院对该证据予以认定。
                        3、原告提供的耒阳市公安局三顺派出所的报警案件登记表、接处警案（事）件登记表和询问笔录两份，用以证明原告系在被告家做事时受伤的事实。被告认为，该证据不能证实原、被告系劳务关系，反而证明了双方系加工承揽关系。本院认为，原告就该证据提出的证明目系证实被告系在被告家中做事而受伤，而未证明原、被告之间的法律关系，故本院对于原告提供的该证据及证明目的均予以认定。

                        综上所述，依照《中华人民共和国侵权责任法》第六条第一款、第十六条，《最高人民法院关于审理人身损害赔偿案件中适用法律若干问题的解释》第十七条第一、二款、第十九条、第二十条、第二十一条、第二十二条、第二十三条第一款、第二十四条、第二十五条、第二十八条第一款，《最高人民法院关于确定民事侵权精神损害赔偿责任若干问题的解释》第八条第二款之规定，判决如下：
                        一、被告谷某在本判决生效之日起十日内支付原告谢某某住院费、误工费、护理费、营养费、住院伙食补助费、交通费、法医鉴定费、残疾赔偿金、精神损害抚慰金、被扶养人生活费共计112188.15元（已核减被告谷某支付的7800元）；
                        二、驳回被告谢某某的其他诉讼请求。
                        如果未按本判决指定的期间履行给付金钱义务，应当依照《中华人民共和国民事诉讼法》第二百五十三条之规定，加倍支付迟延期间的债务利息。（自觉履行的可向当事人直接履行，也可将标的款汇至耒阳市人民法院标的款账户，户名耒阳市人民法院，账号100531698080010001，行号403554700639，开户行中国邮政储蓄银行湖南省耒阳市五一路支行。付款时需要注明案号）
                        案件受理费5504元，由原告谢某某负担3302元，被告谷某负担2202元。
                        如不服本判决，可已在判决书送达之日起十五日内，向本院递交上诉状，并按照对方当事人的人数提出副本，上诉于湖南省衡阳市中级人民法院。
                        审　判　长　　罗东国
                        人民陪审员　　刘　林
                        人民陪审员　　候国清

                        二〇一七年三月十七日
                        代理书记员　　刘　茜
                    </div>
                </div>

                <!--案例的outline概要信息，法院时间当事人等等-->
                <div class="outline box">
                    <h4>裁判文书概要</h4>
                    <hr style="filter: alpha(opacity=100,finishopacity=0,style=3)" width="80%" color=#987cb9 size=3">
                    <div>审理法院： <span id="courtName">中华人民共和国最高人民法院</span></div>
                    <div>案件类型： <span id="property">民事案件</span></div>
                    <div>案由： <span id="reason">提供劳务者受害责任纠纷</span> </div>
                    <div>审理程序: <span id="process">一审案件</span></div>
                    <div>裁判日期: <span id="endDate">2017-03-17</span></div>
                    <div>当事人： <span id="litigant">冯某某、郭某某</span></div>
                    <div>法律依据：<span id="evidence">中华人民共和国民事诉讼法：一百三十一条一款</span></div>
                    <div>关键字：<span id="keywords">合同 劳务</span></div>
                    <div>
                        相似案例：
                        <a target="_Blank" href="http://wenshu.court.gov.cn/content/content?DocID=1a6ebb9e-f279-4cc4-a989-a74500b820bb">http://wenshu.court.gov.cn/content/content?DocID=1a6ebb9e-f279-4cc4-a989-a74500b820bb</a>
                        <a target="_Blank" href="http://wenshu.court.gov.cn/content/content?DocID=1a6ebb9e-f279-4cc4-a989-a74500b820bb">http://wenshu.court.gov.cn/content/content?DocID=1a6ebb9e-f279-4cc4-a989-a74500b820bb</a>
                    </div>
                </div>
            </div>

            <div class="clearfix"> </div>
        </div>
        <div class="clearfix"> </div>
    </div>
</div>
<!-- //main-textgrids -->


<!-- //cases -->
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
                        <li><i class="fa fa-envelope" aria-hidden="true"></i> : <a href="#">winetasters@smail.nju.edu.cn</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-3 w3ls-footer-grid">
                <h4>最新 <span>案例</span></h4>
                <div class="agile-post-grids">
                    <div class="agile-post-grid">
                        <div class="col-md-5 agile-post-left">
                            <a href="single.html">
                                <img src="../images/img1.jpg" alt="" />
                            </a>
                        </div>
                        <div class="col-md-7 agile-post-right">
                            <h5><a href="single.html">Nulla at arcu eu justo blandit.</a></h5>
                            <p><i class="fa fa-calendar-o" aria-hidden="true"></i> 03 Jan 2017</p>
                        </div>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="agile-post-grid">
                        <div class="col-md-5 agile-post-left">
                            <a href="single.html">
                                <img src="../images/img2.jpg" alt="" />
                            </a>
                        </div>
                        <div class="col-md-7 agile-post-right">
                            <h5><a href="single.html">Nulla at arcu eu justo blandit.</a></h5>
                            <p><i class="fa fa-calendar-o" aria-hidden="true"></i> 24 Jan 2017</p>
                        </div>
                        <div class="clearfix"> </div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 w3ls-footer-grid">
                <h4>本站 <span>导航</span></h4>
                <ul>
                    <li><a href="civilCase.html">民事案件</a></li>
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
            <div class="clearfix"> </div>
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
<script src="../js/SmoothScroll.min.js"></script>
<script type="text/javascript" src="../js/move-top.js"></script>
<script type="text/javascript" src="../js/easing.js"></script>
<script type="text/javascript" src="../js/user.js"></script>
<script type="text/javascript" src="../js/search.js"></script>
<script type="text/javascript" src="../js/upload.js"></script>
<script type="text/javascript" src="../js/ajaxfileupload.js"></script>
<script type="text/javascript" src="../js/cookie.js"></script>
<!-- here stars scrolling icon -->
<script type="text/javascript">
    $(document).ready(function() {

        var defaults = {
            containerID: 'toTop', // fading element id
            containerHoverID: 'toTopHover', // fading element hover id
            scrollSpeed: 1200,
            easingType: 'linear'
        };


        $().UItoTop({ easingType: 'easeOutQuart' });

    });
</script>
<!-- //here ends scrolling icon -->
<script src="../js/jarallax.js"></script>
<script type="text/javascript">
    /* init Jarallax */
    $('.jarallax').jarallax({
        speed: 0.5,
        imgWidth: 1366,
        imgHeight: 768
    })
</script>
</body>
</html>
