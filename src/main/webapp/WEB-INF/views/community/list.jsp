<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../../../resources/css/community/style.css"/>
<link rel="stylesheet" href="../../../resources/css/community/index_css.css"/>
<link href="../../../resources/css/include/head/menu_head.css" type="text/css" rel="stylesheet">
<link rel='stylesheet' href="../../../resources/css/include/chat/chat.css">
</head>
<body>
<%@ include file="/WEB-INF/views/include/chat/chat.jsp" %>
 <div class="wrap">
     <%@ include file="/WEB-INF/views/include/head/menu-head.jsp" %>
     <!--nav와 header 끝-->
    <div id="search_case">
        <div id="search_case_inner_div_1"><h3>소통해협</h3></div>
        <div id="search_case_inner_div_3">
           <SELECT NAME=sltSample SIZE=1>
              <option value="" disabled selected hidden>카테고리 선택</option>
              <OPTION VALUE=1>[음식]</OPTION>
              <OPTION VALUE=2>[잡담]</OPTION>
              <OPTION VALUE=3>3번 보기</OPTION>
              <OPTION VALUE=4>4번 보기</OPTION>
           </SELECT>
           <input type="text">
           <i id="search" class="far fa-search"></i>
        </div>
    </div>
    
    <div class="write_body">
    
    <!--index페이지 body작업시작-->
    <div id="index_body_container">
        <table>
            <!--여기에 나중에 spring로 forEach문-->
            <!-- tr 샘플따기 시작 -->
            <tr>
                <td>
                    <div class="tr_inner_container" style="display:block;">
                        
                        <div class="tr_inner_up_div" style="display:flex;">
                        <!--이안에 인제 위에꺼들 채우기-->
                        <div class="board_kinds">[음식]</div>
                        <div class="board_title">제목</div>
                        <div class="board_writer">작성자</div>
                        <div class="board_date">21-11-04</div>
                        <div class="board_view_cnt">101</div>
                        </div>

                        <div class="tr_inner_under_div">
                            <!--이안에 contents 미리보기-->
                            <div class="tr_inner_under_div_contents">
                                미리보기
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <!-- tr 샘플따기 끝 -->



            <!-- tr 샘플따기 시작 -->
            <tr>
                <td>
                    <div class="tr_inner_container" style="display:block;">
                        
                        <div class="tr_inner_up_div" style="display:flex;">
                        <!--이안에 인제 위에꺼들 채우기-->
                        <div class="board_kinds">[음식]</div>
                        <div class="board_title">제목</div>
                        <div class="board_writer">작성자</div>
                        <div class="board_date">21-11-04</div>
                        <div class="board_view_cnt">101</div>
                        </div>

                        <div class="tr_inner_under_div">
                            <!--이안에 contents 미리보기-->
                            <div class="tr_inner_under_div_contents">
                                미리보기
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <!-- tr 샘플따기 끝 -->
            
            <!-- tr 샘플따기 시작 -->
            <tr>
                <td>
                    <div class="tr_inner_container" style="display:block;">
                        
                        <div class="tr_inner_up_div" style="display:flex;">
                        <!--이안에 인제 위에꺼들 채우기-->
                        <div class="board_kinds">[음식]</div>
                        <div class="board_title">제목</div>
                        <div class="board_writer">작성자</div>
                        <div class="board_date">21-11-04</div>
                        <div class="board_view_cnt">101</div>
                        </div>

                        <div class="tr_inner_under_div">
                            <!--이안에 contents 미리보기-->
                            <div class="tr_inner_under_div_contents">
                                미리보기
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <!-- tr 샘플따기 끝 -->


            <!-- tr 샘플따기 시작 -->
            <tr>
                <td>
                    <div class="tr_inner_container" style="display:block;">
                        
                        <div class="tr_inner_up_div" style="display:flex;">
                        <!--이안에 인제 위에꺼들 채우기-->
                        <div class="board_kinds">[음식]</div>
                        <div class="board_title">제목</div>
                        <div class="board_writer">작성자</div>
                        <div class="board_date">21-11-04</div>
                        <div class="board_view_cnt">101</div>
                        </div>

                        <div class="tr_inner_under_div">
                            <!--이안에 contents 미리보기-->
                            <div class="tr_inner_under_div_contents">
                                누구세요
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <!-- tr 샘플따기 끝 -->
            
<!-- tr 샘플따기 시작 -->
            <tr>
                <td>
                    <div class="tr_inner_container" style="display:block;">
                        
                        <div class="tr_inner_up_div" style="display:flex;">
                        <!--이안에 인제 위에꺼들 채우기-->
                        <div class="board_kinds">[음식]</div>
                        <div class="board_title">제목</div>
                        <div class="board_writer">작성자</div>
                        <div class="board_date">21-11-04</div>
                        <div class="board_view_cnt">101</div>
                        </div>

                        <div class="tr_inner_under_div">
                            <!--이안에 contents 미리보기-->
                            <div class="tr_inner_under_div_contents">
                                누구세요
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <!-- tr 샘플따기 끝 -->
            
            

            <!-- tr 샘플따기 시작 -->
            <tr>
                <td>
                    <div class="tr_inner_container" style="display:block;">
                        
                        <div class="tr_inner_up_div" style="display:flex;">
                        <!--이안에 인제 위에꺼들 채우기-->
                        <div class="board_kinds">[잡담]</div>
                        <div class="board_title">제목</div>
                        <div class="board_writer">작성자</div>
                        <div class="board_date">21-11-04</div>
                        <div class="board_view_cnt">101</div>
                        </div>

                        <div class="tr_inner_under_div">
                            <!--이안에 contents 미리보기-->
                            <div class="tr_inner_under_div_contents">
                                미리보기
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <!-- tr 샘플따기 끝 -->
           

            <!-- tr 샘플따기 시작 -->
            <tr>
                <td>
                    <div class="tr_inner_container" style="display:block;">
                        
                        <div class="tr_inner_up_div" style="display:flex;">
                        <!--이안에 인제 위에꺼들 채우기-->
                        <div class="board_kinds">[잡담]</div>
                        <div class="board_title">제목</div>
                        <div class="board_writer">작성자</div>
                        <div class="board_date">21-11-04</div>
                        <div class="board_view_cnt">101</div>
                        </div>

                        <div class="tr_inner_under_div">
                            <!--이안에 contents 미리보기-->
                            <div class="tr_inner_under_div_contents">
                                미리보기
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <!-- tr 샘플따기 끝 -->
            

            <!-- tr 샘플따기 시작 -->
            <tr>
                <td>
                    <div class="tr_inner_container" style="display:block;">
                        
                        <div class="tr_inner_up_div" style="display:flex;">
                        <!--이안에 인제 위에꺼들 채우기-->
                        <div class="board_kinds">[음식]</div>
                        <div class="board_title">제목</div>
                        <div class="board_writer">작성자</div>
                        <div class="board_date">21-11-04</div>
                        <div class="board_view_cnt">101</div>
                        </div>

                        <div class="tr_inner_under_div">
                            <!--이안에 contents 미리보기-->
                            <div class="tr_inner_under_div_contents">
                               미리보기
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <!-- tr 샘플따기 끝 -->
            

            <!-- tr 샘플따기 시작 -->
            <tr>
                <td>
                    <div class="tr_inner_container" style="display:block;">
                        
                        <div class="tr_inner_up_div" style="display:flex;">
                        <!--이안에 인제 위에꺼들 채우기-->
                        <div class="board_kinds">[음식]</div>
                        <div class="board_title">제목</div>
                        <div class="board_writer">작성자</div>
                        <div class="board_date">21-11-04</div>
                        <div class="board_view_cnt">101</div>
                        </div>

                        <div class="tr_inner_under_div">
                            <!--이안에 contents 미리보기-->
                            <div class="tr_inner_under_div_contents">
                                미리보기
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <!-- tr 샘플따기 끝 -->

            
            <!-- tr 샘플따기 시작 -->
            <tr>
                <td>
                    <div class="tr_inner_container" style="display:block;">
                        
                        <div class="tr_inner_up_div" style="display:flex;">
                        <!--이안에 인제 위에꺼들 채우기-->
                        <div class="board_kinds">[음식]</div>
                        <div class="board_title">제목</div>
                        <div class="board_writer">작성자</div>
                        <div class="board_date">21-11-04</div>
                        <div class="board_view_cnt">101</div>
                        </div>

                        <div class="tr_inner_under_div">
                            <!--이안에 contents 미리보기-->
                            <div class="tr_inner_under_div_contents">
                                누구세요
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <!-- tr 샘플따기 끝 -->
            
            

        </table>
    </div>
   </div>

    <!--footer은 글쓰기 페이지엔 존재하지 않으니 index, view_board에만 사용-->
    <footer>
        <div id="footer">
                <div class='page'>
						<i class="fas fa-caret-left"></i>&nbsp;
						<div><span>1</span>&nbsp;<span>2</span>&nbsp;<span>3</span>&nbsp;<span>4</span>&nbsp;<span>5</span>&nbsp;<span>6</span>&nbsp;<span>7</span></div>&nbsp;
						<i class="fas fa-caret-right"></i>
				</div>
        </div>
    </footer>
   </div>
   <!--wrap == container 끝-->
   <script type="text/javascript" src="../../../resources/js/include/chat/chat.js"></script>
</body>
</html>