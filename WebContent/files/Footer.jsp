<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

	<s:div id="div-footer">
		<s:label id="footer-title" cssClass="footer-title" value="WESITE DỊCH VỤ Y TẾ CỘNG ĐỒNG"></s:label>
		<s:div cssClass="div-footer-item">
			<s:label cssClass="footer-title" value="Liên Hệ"></s:label>
			<span>Sở y tế Thừa Thiên Huế - Cổng Dịch vụ y tế cộng đồng</span>
			<span>Địa chỉ : 28 Lê Lợi - Thành phố Huế - Tỉnh Thừa Thiên Huế</span>
			<span>Phonel : 054.3822015</span>
			<span>Fax : 054.3832021</span>
			<span>Email : syt@thuathienhue.gov.vn</span>
		</s:div>
		<s:div cssClass="div-footer-item">
			<s:label cssClass="footer-title" value="Liên Kết"></s:label>
			<s:a href="https://syt.thuathienhue.gov.vn/"><i class="fa fa-hand-o-right"></i> Sở y tế</s:a>
			<s:a href="http://www.thuathienhue.gov.vn/vi-vn/"><i class="fa fa-hand-o-right"></i> Cổng thông tin điện tử</s:a>
			<s:a href="http://www.dav.gov.vn/"><i class="fa fa-hand-o-right"></i> Cục quản lý dược</s:a>
			<s:a href="http://vfa.gov.vn/"><i class="fa fa-hand-o-right"></i> Cục an toàn vệ sinh thực phẩm</s:a>
			<s:a href="http://kcb.vn/"><i class="fa fa-hand-o-right"></i> Cục quản lý khám chữa bệnh</s:a>
		</s:div>
		<s:div cssClass="div-footer-item" cssStyle="max-width: 368px;">
			<s:label cssClass="footer-title" value="Đăng ký nhận bản tin qua mail"></s:label>
			Hãy nhập địa chỉ mail để nhận các bản tin mới nhất từ chúng tôi.
			<form id="nhanEMail" action="nhan-mail.action" method="post">
			<table><tr>
				<td>
					<select class="ct-mail" name="loaiTin">
						<option>Dịch vụ</option>
						<option>Nhu cầu</option>
						<option>Chia sẻ</option>
					</select>
				</td>
				<td><input class="ct-mail" id="email-select" type="text" name="email" placeholder="Email nhận tin..."></td>
				<td><button class="ct-mail" type="submit"><i class="fa fa-envelope-o"></i></button> </td>
			</tr></table>
			</form>
		</s:div>
		<s:div cssClass="clear"></s:div>
	</s:div>
	<s:div cssClass="banquen">
			BẢN QUYỀN THUỘC SỞ Y TẾ TỈNH THỪA THIÊN HUẾ - v.2016
	</s:div>
	<div class="modal fade bs-example-modal-sm" id="success" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body bg-success">
	      	<i class="fa fa-times" style="float: right; cursor: pointer;" title="Đóng" aria-hidden="true" data-dismiss="modal"></i>
	        <p id="messuccess"></p>
	      </div>
	    </div>
	  </div>
	</div>
	<div class="modal fade bs-example-modal-sm" id="error" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body bg-danger">
	      	<i class="fa fa-times" style="float: right; cursor: pointer;" title="Đóng" aria-hidden="true" data-dismiss="modal"></i>
	        <p id="meserror"></p>
	      </div>
	    </div>
	  </div>
	</div>
	<s:div id="tuychon">
		<s:if test="#session.user==null">
			<s:a href="dang-nhap.action"><i class="fa fa-user-plus fa-2x"></i> Đăng nhập/Đăng ký</s:a>
			<s:a href="dang-nhu-cau-moi.action"><i class="fa fa-paper-plane-o fa-2x"></i> Đăng nhu cầu thuê</s:a>
			<s:a href="#email-select"><i class="fa fa-envelope-square fa-2x"></i> Đăng ký nhận mail</s:a>
		</s:if>
		<s:else>
			<s:a href="trang-ca-nhan.action"><i class="fa fa-user fa-2x"></i> Thông tin cá nhân</s:a>
			<s:a href="dang-nhu-cau-moi.action"><i class="fa fa-paper-plane-o fa-2x"></i> Đăng nhu cầu thuê</s:a>
			<s:a href="#email-select"><i class="fa fa-envelope-square fa-2x"></i> Đăng ký nhận mail</s:a>
			<s:a href="dang-xuat.action"><i class="fa fa-sign-out fa-2x"></i> Đăng xuất hệ thống</s:a>
		</s:else>
	</s:div>
	<i id="thong-bao" class="fa fa-bell-o btn-open-popover" aria-hidden="true"></i>
	<div id="divContentHTML" style="display:none">
		<s:if test="#session.ThongBao!=null">
			<s:property value="%{ #session.ThongBao}"/>
		</s:if>
        <s:else>
        	Không có thông báo nào cả!
        </s:else>
    </div>
	<script type="text/javascript">
			$(function () {
		      	$("#thong-bao").popover({
		            title: 'Thông báo',
		            content: $('#divContentHTML').html(),
		            placement: 'top',
		            delay: { show: 500, hide: 100 },
		            html: true
		        });
		      	/* $('#thong-bao').popover('show'); */
		    });
			$('body').on('click', function (e) {
		        $('.btn-open-popover').each(function () {
		          if (!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('.popover').has(e.target).length === 0) {
		            $(this).popover('hide');
		          }
		        });
		      });
	</script>
	<div class="modal fade" id="dangNhapModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		    <div class="modal-content">
			      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				        <h4 class="modal-title">Đăng nhập hệ thống</h4>
			      </div>
			      <form action="dang-nhap-action.action" id="fm-dangnhap" method="post">
			      <div class="modal-body">
			      		<br><s:label value="Tài khoản : " for="taiKhoan"></s:label>
				  		<s:textfield name="taiKhoan" cssClass="form-control"></s:textfield>
				  		<br><s:label value="Mật khẩu : " for="matKhau"></s:label>
				  		<s:password name="matKhau" cssClass="form-control"></s:password>
			      </div>
			      <div class="modal-footer">
				        <button type="submit" class="btn btn-primary">Đăng nhập</button>
			      </div>
			      </form>
		    </div>
	  </div>
	</div>
	
	<script type="text/javascript">
		$("#fm-dangnhap").validate({
			rules : {
				taiKhoan : {
					required : true
				},
				matKhau : {
					required : true
				}
			},
			messages : {
				taiKhoan : {
					required : "Chưa nhập tài khoản!"
				},
				matKhau : {
					required : "Chưa nhập mật khẩu!"
				}
			},
			submitHandler : function(form) {
					var postData = $(form).serializeArray();
				    var formURL = $(form).attr("action");
				    $.ajax({
				        url : formURL,
				        type: "POST",
				        data : postData,
				        beforeSubmit : function (){
				        	$("#loadWhile").attr("style", "display: inherit;");
				        },
				        success:function(data, textStatus, jqXHR) 
				        {
				            if(data.indexOf("thất bại")>-1){
				            	$('#dangNhapModal').modal('hide');
				            	alert("Lỗi xử lý dữ liệu trên server!");
				            } else {
				            	$('#dangNhapModal').modal('hide');
				            	window.location.reload(true);
				            	$('#login').val("true");
				            }
				        },
				        error: function(jqXHR, textStatus, errorThrown) 
				        {
				        	$('#dangNhapModal').modal('hide');
				            alert("Lỗi");    
				        }
				   });
			}
		});
		
		$("#nhanEMail").validate({
			rules : {
				email : {
					required : true,
					email : true
				}
			},
			messages : {
				email : {
					required : "Chưa nhập email!",
					email : "Không phải email"
				}
			},
			submitHandler : function(form) {
					var postData = $(form).serializeArray();
				    var formURL = $(form).attr("action");
				    $.ajax({
				        url : formURL,
				        type: "POST",
				        data : postData,
				        success:function(data, textStatus, jqXHR) 
				        {
				            if(data.indexOf("thất bại")>-1){
				            	$("#email-select").after("Lỗi đăng ký nhận mail!");
				            } else {
				            	$("#email-select").after("Đăng ký nhận mail thành công!");
				            }
				        },
				        error: function(jqXHR, textStatus, errorThrown) 
				        {
				            alert("Lỗi");    
				        }
				   });
			}
		});
	</script>
	