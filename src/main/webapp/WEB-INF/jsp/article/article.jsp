<jsp:useBean id="article" scope="request" type="ee.ttu.idu0200.form.ArticleForm"/>
<%--<jsp:useBean id="article" scope="request" type="ee.ttu.idu0200.model.Article"/>--%>

<%@include file="/WEB-INF/jsp/header.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
  <h1>Edit article</h1>
  <c:if test="${not empty successMessage}">
    <div class="alert alert-success">
      Update successful.
    </div>
  </c:if>
  <c:url var="formLink" value="/article/s?action=save"/>
  <form role="form" class="form-horizontal" action="${formLink}" method="post">
    <div class="form-group">
      <label for="id" class="control-label col-sm-2">Id:</label>
      <div class="col-sm-10">
        <p class="form-control-static" id="id">${article.id}</p>
        <input type="hidden" name="id" value="${article.id}" />
      </div>
    </div>
    <c:set var="isTitleError" value="${not empty bindingResult['article.title']}" />
    <div class="form-group ${isTitleError ? 'has-error' : ''}">
      <label for="title" class="control-label col-sm-2">Title:</label>
      <div class="col-sm-10">
        <input class="form-control" id="title" value="${article.title}" name="title">
        <c:if test="${isTitleError}">
          <span class="help-block"><fmt:message key="${bindingResult['article.title']}"/></span>
        </c:if>
      </div>
    </div>
    <c:set var="isPublishedDateError" value="${not empty bindingResult['article.publishedDate']}" />
    <div class="form-group ${isPublishedDateError ? 'has-error' : ''}">
      <label for="publishedDate" class="control-label col-sm-2">Published date:</label>
      <div class="col-sm-10">
        <input class="form-control" id="publishedDate" value="${article.publishedDate}" type="date" name="publishedDate">
        <c:if test="${isPublishedDateError}">
          <span class="help-block"><fmt:message key="${bindingResult['article.publishedDate']}"/></span>
        </c:if>
      </div>
    </div>
    <c:set var="isContentError" value="${not empty bindingResult['article.content']}" />
    <div class="form-group ${isContentError ? 'has-error' : ''}">
      <label for="content" class="control-label col-sm-2">Content:</label>
      <div class="col-sm-10">
        <textarea class="form-control" id="content" name="content">${article.content}</textarea>
        <c:if test="${isContentError}">
          <span class="help-block"><fmt:message key="${bindingResult['article.content']}"/></span>
        </c:if>
      </div>
    </div>

    <div class="form-group">
      <label class="col-sm-2 control-label"></label>
      <div class="col-sm-10 text-center">
        <button type="submit" class="btn btn-primary ">Submit</button>
        <c:url var="viewAllLink" value="/article/s"/>
        <a href="${viewAllLink}" class="btn btn-default">Cancel</a>
      </div>
    </div>

  </form>

</div>
<%@include file="/WEB-INF/jsp/footer.jsp" %>
