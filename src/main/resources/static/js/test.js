	$("#id").on('input',()=>{
		
		var id_send = $("#id").val();
		if(idOverlap(id_send)){
			$.ajax({
						type:"POST",
						//USER DAO랑 가까운 JAVA OR JSP 파일로 데이터 전송
						url:"idCheck.me",
						data : "id=" + id_send,
						
						success : function(data,textStatus,xhr){
										
							//true - 유저 있음, false - 유저 없음
							if(data == "true") {
								idResultMessage("*이미 사용중인 아이디 입니다.");
							} else {
								idResultMessage("*사용 가능한 아이디 입니다.");
							}
						},
						
						error : function (request,status,err) {
							console.log("code:"+request.status);
						},
					})
		}
		})
	$(window).ready(()=>{
			var result_text = document.getElementById("text-box");
			var result_pwd = document.getElementById("pwd-box");

			if(!result_text.childElementCount) {
				result_text.style.height = "21px";
			}
			if(!result_pwd.childElementCount) {
				result_pwd.style.height = "21px";
			}
		})
	$("#supporter_password").on('input',(e)=>{
			let validatePW = e.target.value;
			
			if(!passwordCheckReg(validatePW)) {
				PWResultMessage("*영문, 숫자 8~20 자리 입력");
				return false;
			}
			if(passwordCheckReg(validatePW)) {
				PWResultMessage("*사용 가능한 비밀번호 입니다");
				return false;
			}
			
			
		})
		
		$("#supporter_password_check").on('input',(e)=>{
			let validatePW = e.target.value;
			let pwdCheck = $("#supporter_password").val();
			
			if(!passwordCheckReg(validatePW)) {
				PWResultMessage("*영문, 숫자 8~20 자리 입력");
				return false;
			}		
			if(!pwd_check(validatePW,pwdCheck)) {
				PWResultMessage("*비밀번호가 일치하지 않습니다");
				return false;
			}
			if(pwd_check(validatePW,pwdCheck)) {
				PWResultMessage("*사용 가능한 비밀번호 입니다");
				return false;
			}				
		})
	
		/*
			전송
		*/
		
		$("#sendbutton").click((e)=>{
			e.preventDefault();
			
			var formValues = $('#signup').serializeArray();
			var check = false;
			formValues.forEach((e)=>{
				console.log(e);
				if(!checkNullCheck(e.value) 
					|| e.value === "") {
					check = true;
				}
 			})
 			
 			if(check){
				console.log(e);
				alert("비어있는 값이 있습니다.");
				return false;
 			}
			
 			if(!koreanCheckReg(formValues[0].value)) {
 				alert("이름에 숫자 또는 영어는 사용할 수 없습니다");
 				return false;
 			}
			if(!koreanCheckReg(formValues[0].value)) {
 				alert("이름에 숫자 또는 영어는 사용할 수 없습니다");
 				return false;
 			}
			
			let pwdCheck = $("#supporter_password_check").val();
			console.log(passwordCheckReg(formValues[2].value));
			console.log(passwordCheckReg(pwdCheck));
			
 			if(!pwd_check(formValues[2].value,pwdCheck)
 			|| !passwordCheckReg(formValues[2].value)
 			|| !passwordCheckReg(pwdCheck)) {
 				alert("비밀번호를 확인해 주세요");
 				return false;
 			}
 			
 			if(!phoneCheckReg(formValues[3].value)) {
 				alert("핸드폰 번호를 확인해 주세요");
 				return false;
 			}
 			
 			
 			if(!postCheckReg(formValues[4].value)) {
 				alert("우편 번호를 확인해 주세요");
 				return false;
 			}
 			
 			$('#signup').submit();
        })