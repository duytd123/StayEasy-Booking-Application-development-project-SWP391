
<%@page import="Model.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="comment_style.css" rel="stylesheet" type="text/css"/>
        <!-- font awesome cdn link  -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
            />
        <link href="stylesheet" rel="stylesheet">
        <link rel="stylesheet" href="list.css">
        <link rel="stylesheet" href="housepage.css">
        <link rel="stylesheet" href="css/list_house_main.css">
        <link rel="stylesheet" href="assets/css/style.min.css">
        <link rel="stylesheet" href="assets/css/dist/css/bootstrap.css">
        <link rel="stylesheet" href="assets/css/dist/css/bootstrap_1.css">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="user.jsp">
        <link rel="stylesheet" href="list_house_main.css">
        <link rel="stylesheet" href="StyleSheet.css">
        <link rel="stylesheet" href="css/housepage.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <%
      Account a = new Account();
            if (request.getAttribute("account") != null) {
                a = (Account) request.getAttribute("account");
            }
        %>
        <section class="comments-container">
            <h1 class="heading">Leave us your comment</h1>

            <p class="comment-title">Your comments on the posts</p>
            <div class="user-comments-container">
                <div class="show-comments">
                    <div class="post-title">
                        from : <span></span>
                        <a href="">view post</a>
                    </div>
                    <form action="AddCommentServlet" method="post">
                        <div class="post-title">Name: <%=a.getFullname() %></div>
                        <input
                            class="comment-box"
                            value="<%=a.getUserid() %>"
                            type="text"
                            name="userid"
                            hidden="true"
                            />
                        <div class="post-title">House:</div>
                        <input
                            class="comment-box"
                            type="number"
                            name="houseid"
                            value=""
                            />
                        <div class="post-title">Date:</div>
                        <input
                            class="comment-box"
                            type="date"
                            name="date"
                            />
                        <div class="post-title">Comment:</div>
                        <input
                            class="comment-box"
                            type="text"
                            name="comment"
                            />
                        <!--            <button
                                      type="submit"
                                      class="inline-delete-btn"
                                      name="open_edit_box"
                                    >
                                      edit 
                                    </button>-->
                        <button
                            type="submit"
                            class="inline-option-btn"
                            name="Add"
                            value="addComment"
                            >Add 
                        </button>
                    </form>
                </div>
            </div>     
        </section>

        <%
List<Comment> list = new ArrayList<Comment>();
if(request.getAttribute("Comment") != null){
    list = (List<Comment>) request.getAttribute("Comment");
}
        %>
        <h1 class="heading">posts comments</h1>
        <%
        for(Comment c : list){
        %>
        <section class="comments">
            <p class="comment-title">Comment</p>
            <div class="box-container">
                <div class="post-title">
                    from : <span></span>
                    <a href="">view post</a>
                </div>
                <div class='box'>
                    <div class='user'>
                        <i class='fas fa-user'></i>
                        <div class='user-info'>
                            <span name="userid" ><%=c.getUserid() %></span>
                            <div name="date"><%=c.getDate() %></div>
                            <div class="text" name="comment"><%=c.getComment() %></div>

                        </div></div>
                    <a class="inline-option-btn" href="NextEditCommentServlet?id=<%=c.getCid()%>">Edit</a>
                    <a class="inline-delete-btn" href="DeleteCommentServlet?id=<%=c.getCid()%>">Delete</a>
                </div></div>

            <%
                }
            %>
        </section>
    </body>
    <script src="admin_script.js"></script>
</html>
