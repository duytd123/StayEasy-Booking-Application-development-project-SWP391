
<%@page import="Model.Comment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
            <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
    />

    <!-- custom css file link  -->
    <link href="comment_style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <%
        Comment c = new Comment();
            if (request.getAttribute("comment") != null) {
                c = (Comment) request.getAttribute("comment");
            }
        %>
        <section class="comment-edit-form">
      <p>edit your comment</p>
      <form action="EditCommentServlet" method="post">
        <input
          type="hidden"
          name="cid"
          value="<%=c.getCid()%>"
        />
        <input
          type="hidden"
          name="userid"
          value="<%=c.getUserid()%>"
        />
        <input
          type="hidden"
          name="houseid"
          value="<%=c.getHouseid()%>"
        />
        <div class="post-title" style="font-size: 25px" >Date:</div>
        <input
            style="font-size: 20px"
          class="comment-box"
          type="date"
          name="date"
          value="<%=c.getDate()%>"
        />
        
        <div class="post-title" style="font-size: 25px">Comment:</div>
        <textarea
          class="comment-box"
          name="comment"
          required
          cols="30"
          rows="10"
          placeholder="please enter your comment"
        ><%=c.getComment()%></textarea>
        <button type="submit" class="inline-btn" name="editcomment">
          edit comment
        </button>
        <div
          class="inline-option-btn"
          onclick="window.location.href = 'NextAddCommentServlet';"
        >
          cancel edit
        </div>
      </form>
    </section>
    </body>
    <script src="admin_script.js"></script>
</html>
