<%--
    Document   : footer
    Created on : 8 Jan, 2012, 11:31:13 AM
    Author     : noushy
--%>

<footer>

    <script type="text/javascript">(function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s); js.id = id;
        js.src = "//connect.facebook.net/en_US/all.js#xfbml=1&appId=92327552391";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));</script>

    <!-- Place this render call where appropriate -->
    <script type="text/javascript">
    (function() {
        var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
        po.src = 'https://apis.google.com/js/plusone.js';
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
    })();
    </script>
    <div id="fb-root"></div>

    <div id="footer" class="row centered-pills">
        <ul class="nav nav-pills">
            <li name="home">
                <a href="/">Home</a>
            </li>
            <li name="help">
                <a href="/views/help.jsp">FAQ</a> 
            </li>
            <li name="appGallery">
                <a href="/views/gallery.jsp">App Gallery</a>
            </li>
            <li name="install">
                <a href="/views/install.jsp">Install</a>
            </li>
            <li>
            <li>
                <a name="feedbackModalLink" href="#">Feedback</a>
            </li>
            <li name="business">
                <a href="/views/business.jsp">Business</a>
            </li>
            <li name="aboutUs">
                <a href="/views/aboutUs.jsp">About Us</a>
            </li>
            <li name="privacy">
                <a href="/views/privacy.jsp">Privacy Policy</a> 
            </li>
            <li name="tos">
                <a href="/views/tos.jsp">TOS</a>
            </li>
        </ul>
    </div>
</footer>
<jsp:include page="/views/common/feedbackmodal.jsp"/>