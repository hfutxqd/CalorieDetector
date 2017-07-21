package com.hack.define.caloriedetector.server;

/**
 * Created by IMXQD on 2017/7/21.
 */
public class StaticData {

    public static final String SearchCSS = "/* CSS Document */\n" +
            "* { padding: 0; margin: 0 }\n" +
            "body { font: 16px/2 Helvetica, STHeiti, Droid Sans Fallback; color: #333; -webkit-text-size-adjust: none; text-size-adjust: none; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; position: relative;\n" +
            "}\n" +
            "img { border: none; vertical-align: top }\n" +
            "a { color: #333; text-decoration: none }\n" +
            "li { list-style: none }\n" +
            "b, i, cite { font-style: normal; font-weight: normal }\n" +
            "label { display: block }\n" +
            "table { border-collapse: collapse; border-spacing: 0; font-size: inherit }\n" +
            ".clearfix:after { content: ''; display: block; clear: both }\n" +
            ".flexbox { display: -webkit-box; display: -moz-box; display: -ms-flexbox; display: -webkit-flex; display: flex }\n" +
            ".flex { -webkit-box-flex: 1; -moz-box-flex: 1; -ms-flex: 1; -webkit-flex: 1; flex: 1 }\n" +
            ".flexAuto { -webkit-box-flex: auto; -moz-box-flex: auto; -ms-flex: auto; -webkit-flex: auto; flex: auto }\n" +
            ".flexCol { -webkit-box-orient: vertical; -moz-box-orient: vertical; -ms-flex-direction: column; -webkit-flex-direction: column; flex-direction: column }\n" +
            ".navBox { border-bottom: #00A3C0 1px solid; }\n" +
            ".nav { height: 35px; background: -ms-linear-gradient(-90deg, #00B1D1, #00A4C7); background: -moz-linear-gradient(-90deg, #00B1D1, #00A4C7); background: -webkit-gradient(linear, left top, left bottom, from(#00B1D1), to(#00A4C7)); }\n" +
            ".nav_index { width: 52px; height: 100%; border-right: #009FBD 1px solid; display: block; background: url(../images/img.png) 10px 6px no-repeat; background-size: 50px auto; }\n" +
            ".nav_link { border-right: #009FBD 1px solid; border-left: #00CBF3 1px solid; }\n" +
            ".nav_link a { color: #FFF; font-size: 13px; padding: 0 8px; display: inline-block; background: url(../images/img.png) 0px -61px no-repeat; background-size: 50px auto; }\n" +
            ".nav_link a:first-child { background: none; }\n" +
            ".nav_btn { width: 52px; height: 100%; border-left: #00CBF3 1px solid; display: block; background: url(../images/img.png) 16px -25px no-repeat; background-size: 50px auto; }\n" +
            ".subNav { height: 36px; display: none; background: #00A4C7; overflow: hidden; }\n" +
            ".subNav li { text-align: center; }\n" +
            ".subNav li a { color: #FFF; font-size: 15px; }\n" +
            ".footer { background: #EEEEEE; border-top: #D8D8D8 1px solid; box-shadow: inset 0 1px 0 #F4F4F4; padding: 17px 0 24px; position: relative; }\n" +
            ".wapInfo { font-size: 13px; color: #999; text-align: center; padding: 6px 0 0; }\n" +
            ".wapInfo a { color: #999; }\n" +
            ".toTop { position: absolute; right: 8px; top: 12px; width: 44px; height: 44px; background: url(../images/img_toTop.png) 0px 0px no-repeat; background-size: 43px auto; }\n" +
            ".toBack { position: absolute; left: 8px; top: 12px; width: 44px; height: 44px; background: url(../images/img_toBack.png) 0px 0px no-repeat; background-size: 43px auto; }\n" +
            ".title1, .title2, .title3 { height: 48px; background: #05B7D6; color: #fff; padding: 0 10px; }\n" +
            ".title1 h2 { line-height: 48px; font-weight: bold;}\n" +
            ".title1 .link { text-align: right; margin: 7px 0 0; }\n" +
            ".title1 .link a { color: #FFF; font-size: 13px; padding: 0 8px; display: inline-block; background: url(../images/img.png) 0px -61px no-repeat; background-size: 50px auto; }\n" +
            ".title1 .link a:first-child { background: none; }\n" +
            ".title2 h2, .title3 h2 { text-align: center; font-weight: 300; line-height: 48px; }\n" +
            ".title2 .back, .title3 .back { font-size: 16px; color: #fff; background: url(../images/img.png) 0px -96px no-repeat; background-size: 50px auto; padding: 0 0 0 18px; margin: 8px 0 0 0; display: block; }\n" +
            ".title2 .menu { display: block; width: 48px; height: 48px; margin: 0 4px 0 0; background: url(../images/img.png) right -126px no-repeat; background-size: 50px auto; }\n" +
            ".title3{ padding-right: 0;}\n" +
            ".title3 h2 b { display: inline-block; padding: 0 0px 0 30px; white-space: nowrap; text-overflow: ellipsis; overflow: hidden; max-width: 124px; }\n" +
            ".title3 .goSearch { display: block; width: 48px; height: 48px; background: url(../images/img_title3.png) 15px -26px no-repeat; background-size: 20px auto; }\n" +
            ".title3 .home { display: block; width: 48px; height: 48px; background: url(../images/img_title3.png) 15px -66px no-repeat; background-size: 20px auto; }\n" +
            ".search { padding: 18px 10px 0; position: relative; }\n" +
            ".search input { -webkit-appearance: none; }\n" +
            ".search input[type=submit] { width: 58px; height: 33px; font-size: 17px; color: #fff; background: #04C2E3; border: none; border-radius: 5px; margin: 0 0 0 16px; }\n" +
            ".search input[type=text] { border: 2px solid #05B7D6; font-size: 12px; color: #333; padding: 7px 5px; height: 15px; border-radius: 5px; display: block; outline: none; }\n" +
            ".hotkey { font-size: 12px; padding: 6px 10px 0; }\n" +
            ".hotkey a { margin-right: 10px; -webkit-tap-highlight-color:rgba(255,255,255,0)}\n" +
            ".count { font-size: 10px; padding: 6px 10px 0; }\n" +
            ".count b { color: #FF7F00; }\n" +
            ".map { padding: 8px 0 0; }\n" +
            ".mapTab { padding: 0 10px; font-size: 15px; }\n" +
            ".mapTab span { text-align: center; font-weight: bold; border-bottom: 1px solid #ccc; padding: 0 0 2px; display: block; }\n" +
            ".mapTab .now { color: #00ADCF; border-bottom: 1px solid #04C2E3; }\n" +
            ".mapCon { }\n" +
            ".mapCon a { padding: 5px 14px; border-bottom: 1px solid #D6D6D6; font-size: 17px; display: block; }\n" +
            ".mapCon a:last-child { border: none; }\n" +
            ".mapCon a b { display: block; background: url(../images/bg_link.png) right center no-repeat; background-size: 8px auto; }\n" +
            ".mapCon cite { float: right; font-size: 12px; margin: 5px 13px 0 0; }\n" +
            ".mapCon i { color: #FF7F00; }\n" +
            ".more { padding: 8px 16px; }\n" +
            ".more a { display: block; background: #C8F0F9; text-align: center; font-size: 12px; line-height: 25px; border-radius: 5px; }\n" +
            ".more span { display: block; text-align: center; font-size: 12px; line-height: 25px; color: #999; }\n" +
            ".content { position: relative; }\n" +
            ".side { width: 122px; position: absolute; top: 0; right: -122px; height: 100%; }\n" +
            ".lever2 { width: 50px; background: #F5F5F5; }\n" +
            ".lever2 span { display: block; width: 50px; text-align: center; font-size: 17px; line-height: 80px; font-weight: bold; position: relative; }\n" +
            ".lever2 span i { display: none; }\n" +
            ".lever2 .now { color: #fff; background: #51CFE6; }\n" +
            ".lever2 .now i { position: absolute; height: 16px; width: 7px; overflow: hidden; background: url(../images/side.png) center center no-repeat; background-size: auto 16px; top: 32px; right: -7px; display: block; }\n" +
            ".lever3 { font-size: 14px; line-height: 34px; height: 100%; background: #fff; }\n" +
            ".lever3 li { }\n" +
            ".lever3 li a { display: block; text-align: center; }\n" +
            ".lever3 .now { color: #FF7F00; }\n" +
            ".heat .info { padding: 20px 18px 0; font-size: 17px; }\n" +
            ".heat .info h3 { line-height: 1.5; }\n" +
            ".heat .info p i { font-size: 12px; color: #666; }\n" +
            ".heat .info p b { color: #FF7F00; }\n" +
            ".heat .info .star { margin: 0 0 0 -3px; width: 75px; display: block; height: 12px; }\n" +
            ".heat .info .star span { display: block; height: 12px; background: url(../images/bg_star.gif) 0 0 repeat-x; background-size: auto 12px; }\n" +
            ".heat .item { margin: 40px 0 0; }\n" +
            ".heat .item dt { padding: 0 18px 2px; border-bottom: #D6D6D6 1px solid; }\n" +
            ".heat .item dd { padding: 0 18px; }\n" +
            ".heat .item .icon { padding: 15px 0 0; font-size: 13px; line-height: 1.5; display: block; }\n" +
            ".heat .item .icon img { margin: 0 0 6px; }\n" +
            ".heat .scale { margin: 15px 0 0; height: 68px; }\n" +
            ".heat .scale span { text-align: center; color: #fff; font-size: 13px; line-height: 1.2; float: left; padding: 14px 0; }\n" +
            ".heat .scale span b { font-size: 20px; }\n" +
            ".heat .scale .s1 { background: #54514A; border-radius: 10px 0 0 10px; }\n" +
            ".heat .scale .s2 { background: #EE9809; }\n" +
            ".heat .scale .s3 { background: #04A9ED; border-radius: 0 10px 10px 0; }\n" +
            ".heat .compo { background: #F5F5F5; border-bottom: #D6D6D6 1px solid; font-size: 15px; line-height: 45px; }\n" +
            ".heat .compo p { border-bottom: #D6D6D6 1px solid; }\n" +
            ".heat .compo p span { float: right; }\n" +
            ".heat .compo p i { font-size: 12px; color: #999; }\n" +
            ".heat .compo p:last-child { border: none; }\n" +
            ".heat .data p span { float: right; font-size: 12px; line-height: 45px; height: 45px; overflow: hidden; }\n" +
            ".heat .data p i { font-size: 20px; color: #FF7F00; }\n" +
            ".heat .data p b { display: inline-block; padding: 0 30px 0 0; background: url(../images/bg_data.png) right center no-repeat; background-size: 22px auto; }\n" +
            ".heat .data p { padding: 0 9px; border-bottom: #D6D6D6 1px solid; font-size: 15px; }\n" +
            ".heat .data p:last-child { border: none; }\n" +
            ".heat .data { border: #D6D6D6 1px solid; border-width: 1px 0; line-height: 45px; padding: 0 9px; background: #F5F5F5; }\n" +
            ".heat { padding: 0 0 20px; }\n" +
            ".swipe { overflow: hidden; visibility: hidden; position: relative; }\n" +
            ".swipe-wrap { overflow: hidden; position: relative; }\n" +
            ".swipe-wrap > div { float: left; width: 100%; position: relative; }\n" +
            "#wrapper {\n" +
            "\tposition: relative;\n" +
            "\theight:100%;\n" +
            "\toverflow: hidden;\n" +
            "}\n" +
            ".suggest{ position: absolute; background: #fff; border: 2px solid #05B7D6; border-width: 0 2px 2px; top: 47px; left: 10px; right: 84px; padding: 0 5px 5px; border-radius: 0 0 5px 5px; font-size: 12px;}\n" +
            ".suggest li{}\n" +
            ".suggest li a{ display: block;}\n" +
            ".suggest{}\n" +
            ".suggest{}\n" +
            "#scroller {\n" +
            "\tposition: absolute;\n" +
            "\tz-index: 1;\n" +
            "\t-webkit-tap-highlight-color: rgba(0,0,0,0);\n" +
            "\twidth: 100%;\n" +
            "\t-webkit-transform: translateZ(0);\n" +
            "\t-moz-transform: translateZ(0);\n" +
            "\t-ms-transform: translateZ(0);\n" +
            "\t-o-transform: translateZ(0);\n" +
            "\ttransform: translateZ(0);\n" +
            "\t-webkit-touch-callout: none;\n" +
            "\t-webkit-user-select: none;\n" +
            "\t-moz-user-select: none;\n" +
            "\t-ms-user-select: none;\n" +
            "\tuser-select: none;\n" +
            "\t-webkit-text-size-adjust: none;\n" +
            "\t-moz-text-size-adjust: none;\n" +
            "\t-ms-text-size-adjust: none;\n" +
            "\t-o-text-size-adjust: none;\n" +
            "\ttext-size-adjust: none;\n" +
            "}\n";

    public static final String USER_AGENT = "Mozilla/5.0 (Windows Phone 10.0; Android 4.2.1; DEVICE INFO) " +
            "AppleWebKit/537.36 (KHTML,like Gecko) Chrome/39.0.2171.71 Mobile Safari/537.36 Edge/12.0";

    public static final String CONTENT_USER_AGENT = "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/6.0)";

    public static final String SEARCH_URL
            = "http://fitness.39.net/tzgj/search/query?keyword=";

}
