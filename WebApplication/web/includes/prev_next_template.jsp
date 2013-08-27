   <%
        int first, last, prev, next, startPos;
        String sPos = request.getParameter("startPos");
        if(sPos==null)
            startPos = 0;
        else
            startPos = Integer.parseInt(request.getParameter("startPos"));
   // int count = Integer.parseInt(request.getAttribute("count").toString());
    int count = Integer.parseInt(request.getParameter("count"));
    //int max = Integer.parseInt(application.getInitParameter("max").toString());
    int max = Integer.parseInt(request.getParameter("max"));
    pageContext.setAttribute("max", max);
    first = 0;
    last = count/max * max;
    pageContext.setAttribute("first", first);
    pageContext.setAttribute("last", last);
    int remaining = count - startPos;
    if(remaining>max)
        next = startPos + max;
    else
        next = 0;
    pageContext.setAttribute("next", next);

    prev = startPos-max;
    if(prev<0)
           prev = 0;
    pageContext.setAttribute("prev", prev);
%>



        <table align="right">
            <tr>
               <td ><b>${param.startPos+1} - ${param.startPos + max}</b></td>
                <td>|<a href="${param.pageName}&startPos=0">first page</a>|</td>
                <td>|<a href="${param.pageName}&startPos=${last}">last page</a>|</td>
                <td>|<a href="${param.pageName}&startPos=${prev}"> << prev </a>|</td>
                <td>|<a href="${param.pageName}&startPos=${next}"> next >></a>|</td>
            </tr>
        </table>