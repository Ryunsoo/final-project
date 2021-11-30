<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="../../../resources/css/include/head/menu_head.css" type="text/css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel='stylesheet' href="../../../resources/css/community/style.css">
<link rel='stylesheet' href="../../../resources/css/include/chat/chat.css">
</head>
<body>
<div id="modal"></div>
<%@ include file="/WEB-INF/views/include/chat/chat.jsp" %>
    <!--헤더자리-->
    <div class="wrap">
      <%@ include file="/WEB-INF/views/include/head/menu-head.jsp" %>
      <link rel="stylesheet" href="../../../resources/css/community/view_board.css"/>
      
      <div id="search_case">
        <div id="search_case_inner_div_1"><h3>소통해협</h3></div>
   	</div>
     <!--nav와 header 끝-->
     <div class="view_body">
     <!--write페이지 body작업시작-->
    <div id="view_body_container" style="display:block;">
        
    
    <!--index페이지 body작업 끝-->
    <!--view페이지 맨위 사용자 정보 -->
    <div id="view_content">
    	<div id="view_page_top_user_info">
    		<div id="content_title">
    			<div id="medal_img">${board.boardCategory}</div>
	      		<div id="title_box">${board.title}</div>
    		</div>
    		
    		<div id="content_info">
    			<div id="username">${board.nickname}</div>
			    <div id="date">${board.parseDate}</div>
			    <div id="view_cnt">${board.viewCnt}</div>
			    <c:if test="${authentication.id eq board.id}">
			    	<div id="dib">|</div>
			    	<div id="modify" onClick="location.href ='http://localhost:9090/community/modify?boardIdx=${board.boardIdx}'">수정</div>
			   	 	<div id="delete" onclick="deleteModal()">삭제</div> 
			    </c:if>
    		</div>
	    </div>
	    <!--view 페이지 맨위 사용자 정보끝-->
	
	   <!--댓글전 사용자 글내용-->
	   <div id="view_contents">${board.content}</div>
    </div>
    
   <!--댓글전 사용자 글내용끝-->
   <!--댓글시작-->
   <div id="comment_wrap">
   <c:if test="${not empty replyList}">
   		<div id="view_comment_notice">
      		<div id="com_line"></div>
      		<div id="comment_cnt">댓글 [${replyCnt}]</div>
      		<div id="com_line_two"></div>
   		</div>
   </c:if>
   <!--댓글모음시작-->
   <div id="view_comments">
      <c:forEach items="${replyList}" var="rl">
      	<div id="comment_item">
	      	 <div id="comment_user">${rl.nickname}</div>
	      	 <div id="item_line"></div>
	      	 <div id="origin-comment" class="comment_content">${rl.content}</div>
	      	 <form id="new-comment" style="display: none;" class="comment_content" action="/community/modify-reply" method="post">
	      	 	<input style="display: none;" type="text" name="boardIdx" value="${board.boardIdx}"/>
	      	 	<input style="display: none;" type="text" name="replyIdx" value="${rl.replyIdx}">
	      	 	<input id="modify-reply-form" type="text" name="content">
	      	 	<button id="modify-reply_btn" type="submit">수정</button>
   	 		 </form>
	      	 <c:choose>
	      	 	<c:when test="${authentication.id eq rl.id}">
	      	 		<div id="comment_edit">
			             <div id="edit_date">${rl.parseDate}</div>
			             <div id="edit_item">
			                <div id="comment_modify" onclick="modifyReply()">수정</div>
			                <div id="comment_delete" onclick="location.href='/community/delete-reply?replyIdx=${rl.replyIdx}&boardIdx=${board.boardIdx}'">삭제</div>
			             </div>
		          	</div>
	      	 	</c:when>
	      	 	<c:otherwise>
	      	 		<div id="comment_date">${rl.parseDate}</div>
	      	 	</c:otherwise>
	      	 </c:choose>
      	</div>
      </c:forEach>
   </div>
   <div id="write_comment_notice">
     		<div id="com_line"></div>
      		<div id="comment_cnt">댓글 등록</div>
      		<div id="com_line_two"></div>   		
   </div>
   <form action="/community/write-reply" method="post">
   <textarea id="write_comment" name="content"></textarea>
   <div id="submit_wrap">
   		<input style="display: none;" type="text" name="boardIdx" value="${board.boardIdx}"/>
   		<input style="display: none;" type="text" name="id" value="${authentication.id}"/>
 		<button type="submit" id="submit_comment">등록</button>
   </div>
   </form>
   <!--댓글모음끝-->
   <!--댓글 끝-->
   </div>   	
   </div>
   <!--view_body_container끝-->
   </div>
     
   
   </div>
   <!--wrap == container 끝-->
  
  <script type="text/javascript">
  
  deleteModal = () => {
      let sendModal = initModal('modal', 3);
      appendTitle(sendModal, '게시글을 삭제하시겠습니까?', true);
      setButton(sendModal, '취소', '삭제');
      setContent(sendModal, true, true);
      modalBlock();
      	
      $('.modal_left_btn').click(function() {
      modalNone();
      	})
      
  }
  
  </script>
  <script type="text/javascript" src="../../../resources/js/include/chat/chat.js"></script>
</body>
<script type="text/javascript">

function modifyReply(){
	
	if (document.getElementById("new-comment").style.display == 'none'){
		document.getElementById("origin-comment").style.display = 'none';
		document.getElementById("new-comment").style.display = 'block';
	}
	
}

</script>
</html>