/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-01-13 09:22:42 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("\t\t<link href=\"/assets/css/bootstrap.min.css\" rel=\"stylesheet\" />\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"/css/style.css\"/>\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"/assets/css/font-awesome.min.css\" />\r\n");
      out.write("        <link href=\"/assets/css/codemirror.css\" rel=\"stylesheet\">\r\n");
      out.write("\t\t<script src=\"/assets/js/ace-extra.min.js\"></script>\r\n");
      out.write("\t\t<script src=\"/assets/js/jquery.min.js\"></script>        \r\n");
      out.write("        <script src=\"/assets/dist/echarts.js\"></script>\r\n");
      out.write("        <script src=\"/assets/js/bootstrap.min.js\"></script>\r\n");
      out.write("        \r\n");
      out.write("      \r\n");
      out.write("       <title></title>\r\n");
      out.write("       </head>\r\n");
      out.write("\t\t\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"page-content clearfix\">\r\n");
      out.write(" <div class=\"alert alert-block alert-success\">\r\n");
      out.write("  <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><i class=\"icon-remove\"></i></button>\r\n");
      out.write("  <i class=\"icon-ok green\"></i>欢迎使用<strong class=\"green\">淘淘商城后台管理系统<small>(v2.0)</small></strong>,你本次登陆时间为2017年3月12日13时34分，登陆IP:192.168.1.118.\t\r\n");
      out.write(" </div>\r\n");
      out.write(" <div class=\"state-overview clearfix\">\r\n");
      out.write("                  <div class=\"col-lg-3 col-sm-6\">\r\n");
      out.write("                      <section class=\"panel\">\r\n");
      out.write("                      <a href=\"#\" title=\"商城会员\">\r\n");
      out.write("                          <div class=\"symbol terques\">\r\n");
      out.write("                             <i class=\"icon-user\"></i>\r\n");
      out.write("                          </div>\r\n");
      out.write("                          <div class=\"value\">\r\n");
      out.write("                              <h1>34522</h1>\r\n");
      out.write("                              <p>商城用户</p>\r\n");
      out.write("                          </div>\r\n");
      out.write("                          </a>\r\n");
      out.write("                      </section>\r\n");
      out.write("                  </div>\r\n");
      out.write("                  <div class=\"col-lg-3 col-sm-6\">\r\n");
      out.write("                      <section class=\"panel\">\r\n");
      out.write("                          <div class=\"symbol red\">\r\n");
      out.write("                              <i class=\"icon-tags\"></i>\r\n");
      out.write("                          </div>\r\n");
      out.write("                          <div class=\"value\">\r\n");
      out.write("                              <h1>140</h1>\r\n");
      out.write("                              <p>分销记录</p>\r\n");
      out.write("                          </div>\r\n");
      out.write("                      </section>\r\n");
      out.write("                  </div>\r\n");
      out.write("                  <div class=\"col-lg-3 col-sm-6\">\r\n");
      out.write("                      <section class=\"panel\">\r\n");
      out.write("                          <div class=\"symbol yellow\">\r\n");
      out.write("                              <i class=\"icon-shopping-cart\"></i>\r\n");
      out.write("                          </div>\r\n");
      out.write("                          <div class=\"value\">\r\n");
      out.write("                              <h1>345</h1>\r\n");
      out.write("                              <p>商城订单</p>\r\n");
      out.write("                          </div>\r\n");
      out.write("                      </section>\r\n");
      out.write("                  </div>\r\n");
      out.write("                  <div class=\"col-lg-3 col-sm-6\">\r\n");
      out.write("                      <section class=\"panel\">\r\n");
      out.write("                          <div class=\"symbol blue\">\r\n");
      out.write("                              <i class=\"icon-bar-chart\"></i>\r\n");
      out.write("                          </div>\r\n");
      out.write("                          <div class=\"value\">\r\n");
      out.write("                              <h1>￥34,500</h1>\r\n");
      out.write("                              <p>交易记录</p>\r\n");
      out.write("                          </div>\r\n");
      out.write("                      </section>\r\n");
      out.write("                  </div>\r\n");
      out.write("              </div>\r\n");
      out.write("             <!--实时交易记录-->\r\n");
      out.write("             <div class=\"clearfix\">\r\n");
      out.write("             <div class=\"t_Record\">\r\n");
      out.write("               <div id=\"main\" style=\"height:300px; overflow:hidden; width:100%; overflow:auto\" ></div>     \r\n");
      out.write("              </div> \r\n");
      out.write("         <div class=\"news_style\">\r\n");
      out.write("          <div class=\"title_name\">最新消息</div>\r\n");
      out.write("          <ul class=\"list\">\r\n");
      out.write("           <li><i class=\"icon-bell red\"></i><a href=\"#\">6月共处理订单3451比，作废为...</a></li>\r\n");
      out.write("           <li><i class=\"icon-bell red\"></i><a href=\"#\">7月商城活动更新。</a></li>\r\n");
      out.write("           <li><i class=\"icon-bell red\"></i><a href=\"#\">XXX商品审核通过！</a></li>\r\n");
      out.write("           <li><i class=\"icon-bell red\"></i><a href=\"#\">XXX商品审核通过！</a></li>\r\n");
      out.write("           <li><i class=\"icon-bell red\"></i><a href=\"#\">XXX商品审核通过！</a></li>\r\n");
      out.write("          </ul>\r\n");
      out.write("         </div> \r\n");
      out.write("         </div>\r\n");
      out.write(" \r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("     $(document).ready(function(){\r\n");
      out.write("\t\t \r\n");
      out.write("\t\t  $(\".t_Record\").width($(window).width()-320);\r\n");
      out.write("\t\t  //当文档窗口发生改变时 触发  \r\n");
      out.write("    $(window).resize(function(){\r\n");
      out.write("\t\t $(\".t_Record\").width($(window).width()-320);\r\n");
      out.write("\t\t});\r\n");
      out.write(" });\r\n");
      out.write("\t \r\n");
      out.write("\t \r\n");
      out.write("        require.config({\r\n");
      out.write("            paths: {\r\n");
      out.write("                echarts: '/assets/dist'\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("        require(\r\n");
      out.write("            [\r\n");
      out.write("                'echarts',\r\n");
      out.write("\t\t\t\t'echarts/theme/macarons',\r\n");
      out.write("                'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表\r\n");
      out.write("                'echarts/chart/bar'\r\n");
      out.write("            ],\r\n");
      out.write("            function (ec,theme) {\r\n");
      out.write("                var myChart = ec.init(document.getElementById('main'),theme);\r\n");
      out.write("               option = {\r\n");
      out.write("    title : {\r\n");
      out.write("        text: '月购买订单交易记录',\r\n");
      out.write("        subtext: '实时获取用户订单购买记录'\r\n");
      out.write("    },\r\n");
      out.write("    tooltip : {\r\n");
      out.write("        trigger: 'axis'\r\n");
      out.write("    },\r\n");
      out.write("    legend: {\r\n");
      out.write("        data:['所有订单','待付款','已付款','代发货']\r\n");
      out.write("    },\r\n");
      out.write("    toolbox: {\r\n");
      out.write("        show : true,\r\n");
      out.write("        feature : {\r\n");
      out.write("            mark : {show: true},\r\n");
      out.write("            dataView : {show: true, readOnly: false},\r\n");
      out.write("            magicType : {show: true, type: ['line', 'bar']},\r\n");
      out.write("            restore : {show: true},\r\n");
      out.write("            saveAsImage : {show: true}\r\n");
      out.write("        }\r\n");
      out.write("    },\r\n");
      out.write("    calculable : true,\r\n");
      out.write("    xAxis : [\r\n");
      out.write("        {\r\n");
      out.write("            type : 'category',\r\n");
      out.write("            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']\r\n");
      out.write("        }\r\n");
      out.write("    ],\r\n");
      out.write("    yAxis : [\r\n");
      out.write("        {\r\n");
      out.write("            type : 'value'\r\n");
      out.write("        }\r\n");
      out.write("    ],\r\n");
      out.write("    series : [\r\n");
      out.write("        {\r\n");
      out.write("            name:'所有订单',\r\n");
      out.write("            type:'bar',\r\n");
      out.write("            data:[120, 49, 70, 232, 256, 767, 1356, 1622, 326, 200,164, 133],\r\n");
      out.write("            markPoint : {\r\n");
      out.write("                data : [\r\n");
      out.write("                    {type : 'max', name: '最大值'},\r\n");
      out.write("                    {type : 'min', name: '最小值'}\r\n");
      out.write("                ]\r\n");
      out.write("            }           \r\n");
      out.write("        },\r\n");
      out.write("        {\r\n");
      out.write("            name:'待付款',\r\n");
      out.write("            type:'bar',\r\n");
      out.write("            data:[26, 59, 30, 84, 27, 77, 176, 1182, 487, 188, 60, 23],\r\n");
      out.write("            markPoint : {\r\n");
      out.write("                data : [\r\n");
      out.write("                    {name : '年最高', value : 1182, xAxis: 7, yAxis: 1182, symbolSize:18},\r\n");
      out.write("                    {name : '年最低', value : 23, xAxis: 11, yAxis: 3}\r\n");
      out.write("                ]\r\n");
      out.write("            },\r\n");
      out.write("           \r\n");
      out.write("\t\t\t\r\n");
      out.write("        }\r\n");
      out.write("\t\t, {\r\n");
      out.write("            name:'已付款',\r\n");
      out.write("            type:'bar',\r\n");
      out.write("            data:[26, 59, 60, 264, 287, 77, 176, 122, 247, 148, 60, 23],\r\n");
      out.write("            markPoint : {\r\n");
      out.write("                data : [\r\n");
      out.write("                    {name : '年最高', value : 172, xAxis: 7, yAxis: 172, symbolSize:18},\r\n");
      out.write("                    {name : '年最低', value : 23, xAxis: 11, yAxis: 3}\r\n");
      out.write("                ]\r\n");
      out.write("            },\r\n");
      out.write("           \r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t, {\r\n");
      out.write("            name:'代发货',\r\n");
      out.write("            type:'bar',\r\n");
      out.write("            data:[26, 59, 80, 24, 87, 70, 175, 1072, 48, 18, 69, 63],\r\n");
      out.write("            markPoint : {\r\n");
      out.write("                data : [\r\n");
      out.write("                    {name : '年最高', value : 1072, xAxis: 7, yAxis: 1072, symbolSize:18},\r\n");
      out.write("                    {name : '年最低', value : 22, xAxis: 11, yAxis: 3}\r\n");
      out.write("                ]\r\n");
      out.write("            },\r\n");
      out.write("           \r\n");
      out.write("\t\t}\r\n");
      out.write("    ]\r\n");
      out.write("};\r\n");
      out.write("                    \r\n");
      out.write("                myChart.setOption(option);\r\n");
      out.write("            }\r\n");
      out.write("        );\r\n");
      out.write("    </script> \r\n");
      out.write("     </div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
