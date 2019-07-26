package org.apache.jsp.PaperEditor;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class PaperEditor_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\r');
      out.write('\n');
if (session.getAttribute("email") == null) {
        response.sendRedirect("/EZcloLS/index/login.jsp");
    }
      out.write(" \r\n");
      out.write("<!doctype html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta charset=\"utf-8\">\r\n");
      out.write("        <title>EZcloLS Edit Cloze</title>\r\n");
      out.write("        <link href=\"/EZcloLS/css/EditStyle.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css\" integrity=\"sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS\" crossorigin=\"anonymous\">\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width,initial-scale=1,shrink-to-fit=no\">\r\n");
      out.write("    </head>\r\n");
      out.write("\r\n");
      out.write("    <body>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"modal fade\" id=\"exampleModal2\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\r\n");
      out.write("            <div class=\"modal-dialog modal-xl\" role=\"document\">\r\n");
      out.write("                <div class=\"modal-content\">\r\n");
      out.write("                    <div class=\"modal-header\">\r\n");
      out.write("                        <h5 class=\"modal-title\" id=\"exampleModalLabel2\">使用說明</h5>\r\n");
      out.write("                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\r\n");
      out.write("                            <span aria-hidden=\"true\">&times;</span>\r\n");
      out.write("                        </button>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"modal-body \">\r\n");
      out.write("                        <form>\r\n");
      out.write("                            <div class=\"row\">\r\n");
      out.write("                                <div class=\"form-group readme-panel col-12 col-md-6\">\r\n");
      out.write("                                    <img src=\"/EZcloLS/img/editor.svg\"  alt=\"\"/>\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <div class=\"readme-exp-panel list-group col-12 col-md-6\">\r\n");
      out.write("                                    <div class=\"form-group list-group-item list-group-flush\">\r\n");
      out.write("                                        <img src=\"/EZcloLS/img/cloze.svg\"  alt=\"\"/>\r\n");
      out.write("                                        <label for=\"recipient-name\" class=\"col-form-label\">克漏字工具</label>\r\n");
      out.write("                                        <p>製作填空-可將反白的文字轉換為填空</p>\r\n");
      out.write("                                        <p>取消填空-可將選取的填空轉換為文字</p>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    <div class=\"form-group list-group-item\">\r\n");
      out.write("                                        <img src=\"/EZcloLS/img/toolbar.svg\" alt=\"\"/>\r\n");
      out.write("                                        <label for=\"recipient-name\" class=\"col-form-label\">基本工具列</label>\r\n");
      out.write("                                        <p>提供文字編輯的基本操作、如復原、重做、複製、 剪下、貼上、儲存</p>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    <div class=\"form-group list-group-item\">\r\n");
      out.write("                                        <img src=\"/EZcloLS/img/paper.svg\" alt=\"\"/>\r\n");
      out.write("                                        <label for=\"recipient-name\" class=\"col-form-label\">文章編輯</label>\r\n");
      out.write("                                        <p>使用者您可將需要的文章放至此處編輯</p>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    <div class=\"form-group list-group-item\">\r\n");
      out.write("                                        <img src=\"/EZcloLS/img/rename.svg\" alt=\"\"/>\r\n");
      out.write("                                        <label for=\"recipient-name\" class=\"col-form-label\">試卷名稱</label>\r\n");
      out.write("                                        <p>此處將會顯示您的試卷名稱</p>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </form>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"container-fluid\">        \r\n");
      out.write("            <div class=\"header row align-items-end\">\r\n");
      out.write("                <div class=\"logo col-6 col-md-8 d-flex align-items-end\">\r\n");
      out.write("                    <h1><a href=\"#\"><img src=\"/EZcloLS/img/logo-f.svg\" alt=\"\"/></a></h1>\r\n");
      out.write("                    <p>&nbsp;&nbsp;&nbsp;</p>\r\n");
      out.write("                    <b class=\"d-none d-md-block\">Memorize Vacabulary By Cloze</b> \r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("                <div class=\"account-box col-6 col-md-4 \">\r\n");
      out.write("                    <div class=\"dropdown d-flex justify-content-end \">\r\n");
      out.write("                        <button class=\"account-botton btn btn-secondary dropdown-toggle \" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\"> \r\n");
      out.write("                            <span>一般會員 howard</span> \r\n");
      out.write("                        </button>\r\n");
      out.write("                        <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\"> \r\n");
      out.write("                            <a class=\"dropdown-item\" href=\"#\">帳號資訊</a> <a class=\"dropdown-item\" href=\"#\">登出</a> \r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"row mt-2\">\r\n");
      out.write("                <div class=\"rename col-12 col-lg-4 p-3\" >\r\n");
      out.write("                    <div class=\"rename-box\">\r\n");
      out.write("                        <div class=\"input-group\">\r\n");
      out.write("                            <input id = \"name\" type=\"text\" minlength=\"1\"  maxlength=\"20\" class=\"form-control\" placeholder=\"Original file name\" aria-label=\"Recipient's username\" aria-describedby=\"button-addon2\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${Paper.getName()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("                            <div class=\"input-group-append\">\r\n");
      out.write("                                <button class=\"btn btn-outline-info\" type=\"button\" id=\"button-addon2\">重新命名</button>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("                <div class=\"toolbar-box col-12 col-lg-8 p-3\">\r\n");
      out.write("                    <form id=\"mainform\" class=\"row\" >\r\n");
      out.write("                        <div class=\"toolbar-btn col-2 text-center\" onClick=\"previous()\">\r\n");
      out.write("                            <button type=\"button\" class=\"badge-pill w-100 \" > 復原 </button>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"toolbar-btn col-2 text-center\" onClick=\"next()\">\r\n");
      out.write("                            <button type=\"button\" class=\"badge-pill w-100\"> 重做 </button>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"toolbar-btn col-2 text-center\" onClick=\"copy()\">\r\n");
      out.write("                            <button type=\"button\" class=\"badge-pill w-100\"> 複製 </button>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"toolbar-btn col-2 text-center\" onClick=\"cut()\">\r\n");
      out.write("                            <button type=\"button\" class=\"badge-pill w-100\"> 剪下 </button>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"col-2 text-center toolbar-btn\" onClick=\"paste()\">\r\n");
      out.write("                            <button type=\"button\" class=\"badge-pill w-100\"> 貼上 </button>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"save-btn col-2 text-center\" onclick=\"save_s(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${Paper.getF_number()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(',');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${Paper.getT_number()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(")\">\r\n");
      out.write("                            <button type=\"button\" class=\"badge-pill w-100\" >存檔 </button>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </form>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"row\">\r\n");
      out.write("                <div class=\"article\">以下請貼上想要製作克漏字的文章</div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"row\">\r\n");
      out.write("                <div id=\"cloze_toolbar\" class=\"make-cloze-bar drop-shadow\">\r\n");
      out.write("                    <div class=\"d-inline-flex\">\r\n");
      out.write("                        <div class=\"make-cloze-panel\">\r\n");
      out.write("                            <div class=\"make-clo-btn\" onClick=\"cloze()\"> \r\n");
      out.write("                                <img src=\"/EZcloLS/img/excavator.svg\" alt=\"\"/>\r\n");
      out.write("                                <p>製作填空</p>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div class=\"make-clo-btn\" onClick=\"uncloze()\"> \r\n");
      out.write("                                <img src=\"/EZcloLS/img/brickwall.svg\" alt=\"\"/>\r\n");
      out.write("                                <p>取消填空</p>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div class=\"make-clo-btn\"  data-toggle=\"modal\" data-target=\"#exampleModal2\"> \r\n");
      out.write("                                <img src=\"/EZcloLS/img/manual.svg\" alt=\"\"/>\r\n");
      out.write("                                <p>使用說明</p>\r\n");
      out.write("                            </div>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"d-inline-flex w-100 m-2\">\r\n");
      out.write("                                縮放:\r\n");
      out.write("                                <select id=\"frame_size_selector\" onchange=\"setframe_size()\">\r\n");
      out.write("                                    <option value=\"0.5\">50%</option>\r\n");
      out.write("                                    <option value=\"0.75\">75%</option>\r\n");
      out.write("                                    <option value=\"1\" selected >100%</option>\r\n");
      out.write("                                    <option value=\"1.25\">125%</option>\r\n");
      out.write("                                    <option value=\"1.5\">150%</option>\r\n");
      out.write("                                    <option value=\"2\">200%</option>\r\n");
      out.write("                                </select>\r\n");
      out.write("                            </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <span id=\"text-cloze-bar\" class=\"toggle\">克漏字作業區</span>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                </div>\r\n");
      out.write("                <iframe id=\"edit_panel\" class=\" flex-fill cloze-area\" src=\"/EZcloLS/ConstructPaper?T_Number=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${Paper.getT_number()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" ></iframe>\t\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"row footer\">\r\n");
      out.write("                <div class=\"footertext\">\r\n");
      out.write("                    <h5>臺北市職能發展學院</h5>\r\n");
      out.write("                    <h6>臺北市士林區士東路301號</h6>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js\" integrity=\"sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut\" crossorigin=\"anonymous\"></script> \r\n");
      out.write("        <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js\" integrity=\"sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("        <script src=\"/EZcloLS/js/EditorScript.js\"></script>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
