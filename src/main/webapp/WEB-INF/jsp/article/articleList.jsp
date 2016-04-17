<%@include file="/WEB-INF/jsp/header.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="<c:url value='/static/article/articleList.js'/> " ></script>
<div class="container">
  <h1>Article list</h1>
  <table class="table-striped table-bordered table-hover  col-sm-12">
    <tr>
      <th>Title</th>
      <th>Published date</th>
      <th>Action</th>
    </tr>
    <jsp:useBean id="articleList" scope="request" type="java.util.List"/>
    <c:forEach items="${articleList}" var="article">
      <tr>
        <td>${article.title}</td>
        <td>${article.publishedDate}</td>
        <td>
          <c:url value="/article/articleservice/?id=${article.id}" var="articleServiceLink"/>
          <a href="${articleServiceLink}" data-toggle="modal" data-remote="false" data-target="#modal" class="btn btn-default btn-sm">
            <span class="glyphicon glyphicon-eye-open"></span>
            View
          </a>

          <c:url var="editLink" value="/article/s?id=${article.id}"/>
          <a class="btn btn-default btn-sm" href="${editLink}">
            <span class="glyphicon glyphicon-edit"></span>
            Edit
          </a>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>

<!-- Modal -->
<div id="modal" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Article details</h4>
      </div>
      <div class="modal-body">

        <form class="form-horizontal" role="form" id="form">
          <div class="form-group">
            <label for="id" class="col-sm-2 control-label">Id:</label>
            <p class="col-sm-10 form-control-static" id="id"></p>
          </div>
          <div class="form-group">
            <label for="content" class="col-sm-2 control-label">Content:</label>
            <div class="col-sm-10">
              <textarea class=" form-control" id="content" rows="5"></textarea>
            </div>
          </div>
        </form>

        <div class="progress" id="progress" style="display: none;">
          <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%"></div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


<%@include file="/WEB-INF/jsp/footer.jsp" %>
