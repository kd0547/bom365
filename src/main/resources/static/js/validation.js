/**
 * 다나와 공용 validation 
 * validation + hadler 통합 Version
 * @constructor DanawaMemberValidation
 * @author whfkfdakf75@danawa.com
 */
 var DanawaMemberValidation = function() {

	/**
	 * DOM ID 객체
	 * validation 대상 아이디 주입 필요 
	 * @private
	 * @field
	 * @type {Object}
	 */

	var id = {};

	/**
	 * 상태 정보
	 * 
	 * @private
	 * @field
	 * @type {Object}
	 */
	var status = {
		isIdRock : false,
		isPasswordRock : false,
		isPasswordConfirmRock : false,
		isNameRock : false,
		isNicknameRock : false,
		isEmailRock : false,
		isSubmitRock : false,
		isCheckMemberPasswordRock : false,
		message : {
			id : '',
			password : '',
			passwordConfirm : '',
			name : '',
			nickname : '',
			email : '',
			mobile : '',
			checkMemberPassword : ''
		}, 
		passwordCheck :	{
			length : false,
			idComparison : false,
			specialCharacter : false,
			combination : false
		}
	};

	/**
	 * 상태값을 반환한다.
	 * 
	 * @public
	 * @function
	 * @throws
	 * @returns
	 */
	this.getStatus = function() {
		return status;
	}

	/**
	 * DOM<br /> - jQuery 객체<br /> - 수정하지 않는것을 권장
	 * 
	 * @private
	 * @field
	 * @type {Object}
	 */
	var target = {};

	/**
	 * 하위 뷰 모델 정보
	 * 
	 * @private
	 * @field
	 * @type {Object}
	 */
	var model = {
		validator : {}
	};
	
	/**
	 * 모델을 초기화 한다.
	 * 
	 * @public
	 * @function
	 * @throws
	 * @returns
	 */
	this.initialize = function(validatonTagetId) {
		id=validatonTagetId;
		var ValidatonTarget = $('body');
		if ($('#'+id.target).length > 0)  {
			ValidatonTarget = $('#'+id.target);
		}
		target = ValidatonTarget;
		
		/**
		 * 정보 입력 실시간 검증
		 */
		// taget setting 
		if (!_.isEmpty(id.member.id)) {
			target.on('keyup', '#' + id.member.id, idHeandler); // 아이디
		}
		if (!_.isEmpty (id.member.nowPassword)) {
			target.on('blur', '#' + id.member.nowPassword, checkMemberPasswordHandler); //현재 패스워드 확인
		}
		if (!_.isEmpty (id.member.password)) {
			target.on('keyup', '#' + id.member.password, passwordHandler); //패스워드
		}
		if (!_.isEmpty (id.member.passwordConfirm)) {
			target.on('keyup', '#' + id.member.passwordConfirm, passwordConfirmHandler); //패스워드 확인
		}
		if (!_.isEmpty (id.member.nickname)) {
			target.on('keyup', '#' + id.member.nickname, nicknameHandler); //닉네임
		}
		if (!_.isEmpty (id.member.name)) {
			target.on('keyup', '#' + id.member.name, nameHandler); //닉네임
		}

		if(!_.isUndefined(id.member.email) && !_.isEmpty (id.member.email)) {
			if (!_.isEmpty (id.member.email.emailFirst) && !_.isEmpty (id.member.email.emailSecond)) {
				target.on('blur', '#' + id.member.email.emailSecond, emailHandler); //이메일
			}
			if (!_.isEmpty (id.member.email.emailFirst) && _.isEmpty (id.member.email.emailSecond)) {
				target.on('blur', '#' + id.member.email.emailFirst, emailHandler); //이메일
			}
		}
	};
	

	/**
	 * ********************************************id 유효성 검사**********************************************
	 */
	var isIdValid = function(value) {
		var flag = false;
		var method = 'POST';
		var action = '/checkedId';
		var data = {
			memberId : value
		}

		$.ajax({
			type : method,
			url : action,
			data : data,
			async : false,
			success : function(data) {
				MEMBER_LOG_UTILS.log(data);

				if (data.result.code === '200') {
					status.message.id = data.result.message;
					if (data.result.status === '0') {
						flag = true;
					} else {
						flag = false;
					}
				}
			}
		});

		return flag;
	};

	/**
	 * ********************************************패스워드 유효성 검사**********************************************
	 */
	// 패스워드 유효성 검사
	var isPasswordValid = function(value) {
		/** ********************************************정의********************************************** */
		
		//패스워드 길이  !, @, #, $, %, ^, &, *
		var usingLengthMin = (/.{8}/.test(value));
		var usingLengthMax = !(/.{21}/.test(value));
		//허용가능한 특수문자 
		var usingCharacter = !(/(?=.*[^a-zA-Z0-9\!\@\#\$\%\^\&\*]).+/.test(value));
		//영문, 숫자, 특수문자조합
		var usingCombination = (/(?=.*[a-zA-Z])(?=.*[\!\@\#\$\%\^\&\*])(?=.*[0-9]).+/.test(value));
		//영문, 숫자조합
		var usingCombinationEnglishNumber = (/(?=.*[a-zA-Z])(?=.*[0-9]).+/.test(value));
		//특수문자조합
		var usingCombinationSpecialCharacter = (/(?=.*[\!\@\#\$\%\^\&\*]).+/.test(value));
		// 공백 사용 불가
		var usingSpace = (/\s/.test(value));
		
		//UI에 체크형태로 검증내용을 보여주기 위한 로직
		//영문 기준 8자 이상 20자 이하
		if(id.checkArea != null) {
			if (usingLengthMin && usingLengthMax) {
				status.passwordCheck.length = true;
				$('#' + id.checkArea.passwordLength).addClass("on checked");
				$('#' + id.checkArea.passwordLength ).siblings(".blind").text("충족");
			} else {
				status.passwordCheck.length = false;
				$('#' + id.checkArea.passwordLength).removeClass("on checked");
				$('#' + id.checkArea.passwordLength ).siblings(".blind").text("미흡");
			}
			
			//아이디와 4자 이상 동일한 문자 사용 불가
			if (value != "" && idSame(value)) {
				status.passwordCheck.idComparison = true;
				$('#' + id.checkArea.passwordIdComparison).addClass("on checked");
				$('#' + id.checkArea.passwordIdComparison ).siblings(".blind").text("충족");
			} else {
				status.passwordCheck.idComparison = false;
				$('#' + id.checkArea.passwordIdComparison).removeClass("on checked");
				$('#' + id.checkArea.passwordIdComparison ).siblings(".blind").text("미흡");
			}
			
			//특수문자 조합
			if (usingCombinationSpecialCharacter && usingCharacter) {
				status.passwordCheck.specialCharacter = true;
				$('#' + id.checkArea.passwordSpecialCharacter).addClass("on checked");
				$('#' + id.checkArea.passwordSpecialCharacter ).siblings(".blind").text("충족");
			} else {
				status.passwordCheck.specialCharacter = false;
				$('#' + id.checkArea.passwordSpecialCharacter).removeClass("on checked");
				$('#' + id.checkArea.passwordSpecialCharacter ).siblings(".blind").text("미흡");
			}
			
			//영문, 숫자 조합
			if (usingCombinationEnglishNumber) {
				status.passwordCheck.combination = true;
				$('#' + id.checkArea.passwordCombination).addClass("on checked");
				$('#' + id.checkArea.passwordCombination ).siblings(".blind").text("충족");
			} else {
				status.passwordCheck.combination = false;
				$('#' + id.checkArea.passwordCombination).removeClass("on checked");
				$('#' + id.checkArea.passwordCombination ).siblings(".blind").text("미흡");
			}			
		}

		// 아이디와 동일한 4자 이상 중복
		function idSame(value) {
			var flag = true;
			var userId;

			if (_.isUndefined($('#' + id.member.id).val()) === false
					&& _.isNull($('#' + id.member.id).html()) === false) {
				if (_.isNull($('#' + id.member.id).html()) === false
						&& _.isEmpty($('#' + id.member.id).html()) === false) {
					userId = $('#' + id.member.id).html();
				}
				
				if (_.isUndefined($('#' + id.member.id).val()) === false
						&& _.isEmpty($('#' + id.member.id).html()) === false || $('#' + id.member.id).html() == '') {
					userId = $('#' + id.member.id).val();
				}

				if (value.length >= 8 && userId) {
					for (var i = 0; i < userId.length - 3; i++) {
						if (value.indexOf(userId.substr(i, 4)) !== -1) {
							flag = false;
						}
					}
				}
			}

			return flag;
		}

		/** ********************************************메세지********************************************** */
		// 패스워드 공백
		if (_.isEmpty(value) === true) {
			status.message.password = '비밀번호를 입력해 주세요.';
			return false;
		}
		//패스워드 길이 미만
		if (!usingLengthMin) {
			status.message.password = '너무 짧습니다. 최소 8자 이상 입력하세요.';
			return false;
		}
		//패스워드 길이 초과
		if (!usingLengthMax) {
			status.message.password = '너무 깁니다. 최대 20자 이하로 입력하세요.';
			return false;
		}
		
		// 공백 사용 불가
		if (usingSpace) {
			status.message.password = '공백은 사용하실 수 없습니다.';
			return false;
		}
		// 특수문자
		if (!usingCharacter) {
			status.message.password = '특수문자는 !, @, #, $, %, ^, &, * 만 가능합니다.';
			return false;
		}
		
		// 영문숫자조합
		if (!usingCombination) {
			status.message.password = '영문과 숫자와 특수문자를 조합해서 입력해 주세요.';
			return false;
		}
		
		// 아이디와 동일한 4자 이상 중복
		if (!idSame(value)) {
			status.message.password = '아이디와 동일한 문자 4자 이상은 사용하실 수 없습니다.';
			return false;
		}

		status.message.password = '';
		return true;

	};// 패스워드 유효성 검사

	/**
	 * ********************************************패스워드확인 유효성 검사**********************************************
	 */
	var isPasswordConfirmValid = function(value) {
		// 패스워드 확인 공백
		if (_.isEmpty(value) === true) {
			status.message.passwordConfirm = '비밀번호 확인을 입력해 주세요.';
			return false;
		}
		// 패스워드와 패스워드확인 비교
		if ($('#' + id.member.password).val() !== value) {
			status.message.passwordConfirm = '비밀번호가 일치하지 않습니다.';
			return false;
		}

		// 이전 패스워드랑 동일 할 경우
		if ($('#' + id.member.nowPassword).val() !== null
				&& $('#' + id.member.nowPassword).val() !== undefined) {
			if ($('#' + id.member.nowPassword).val() === $('#' + id.member.password).val()) {
				status.message.passwordConfirm = '현재 비밀번호와 같습니다. 다시 입력해 주세요';
				return false;
			}
		}

		status.message.passwordConfirm = '';
		return true;
	};
	
	/**
	 * ********************************************이름 유효성 검사**********************************************
	 */
	
	var isNameValid = function(value) {
		if (_.isEmpty(value) === true) {
			status.message.name = '이름을 입력해 주세요.';
			return false;
		}
		
		var flag = false;
		var method = 'POST';
		var action = '/checkedName';
		var data = {
				memberName : value
		};
		
		$.ajax({
			type : method,
			url : action,
			data : data,
			async : false,
			success : function(data) {
				MEMBER_LOG_UTILS.log(data);

				if (data.result.code === '200') {
					status.message.name = data.result.message;
					if (data.result.status === '0') {
						flag = true;
					} else {
						flag = false;
					}
				}
			}
		});
		return flag;
	};
	
	/**
	 * ********************************************닉네임 유효성 검사**********************************************
	 */
	var isNicknameValid = function(value) {
		var flag = false;
		var method = 'POST';
		var action = '/checkedNickname';
		var data = {
			memberNickname : value
		};
		
		$.ajax({
			type : method,
			url : action,
			data : data,
			async : false,
			success : function(data) {
				MEMBER_LOG_UTILS.log(data);

				if (data.result.code === '200') {
					status.message.nickname = data.result.message;
					if (data.result.status === '0') {
						flag = true;
					} else {
						flag = false;
					}
				}
			}
		});
		return flag;
	}
	
	/**
	 * ********************************************이메일 유효성 검사**********************************************
	 */
	var isEmailValid = function(value) {
		var flag = false;
		var method = 'POST';
		var action = '/checkedEmail';
		var data = {};
		var id = $('#danawa-member-dormantEmailAuth-id').val();

		if (id == null || id == undefined) {
			data = {
				memberEmail: value
			}
		} else {
			data = {
				memberEmail: value,
				memberId: id
			}
		}

		/**
		 * MEMBER-1684 드림위즈 이메일 이용자 이메일 변경 안내의 건
		 * 드림위즈 전환건 마무리 될 경우 삭제 예정.
		 * else 내용을 제외하고 if문 제거
		 */
		if (value.indexOf('dreamwiz.com') >= 0) {
			status.message.email = '종료된 이메일 주소입니다. 다시 입력해주시기 바랍니다.';
			flag = false;
		} else {
			$.ajax({
				type : method,
				url : action,
				data : data,
				async : false,
				success : function(data) {
					MEMBER_LOG_UTILS.log(data);

					if (data.result.code === '200') {
						status.message.email = data.result.message;
						if (data.result.status === '0') {
							flag = true;
						} else {
							flag = false;
						}
					}
				}
			});
		}

		return flag;
	}

	/**
	 * ********************************************아이디 + 비밀번호 유효성 검사**********************************************
	 */
	var isCheckMembmerPwdValid = function(value) {
		var flag = false;

		// 이메일 공백
		if (_.isEmpty(value) === true) {
			status.message.checkMemberPassword = '비밀번호를 입력해 주세요';
			return false;
		} else {
			var method = 'POST';
			var action = '/checkMemberPwdAjax';
			var data = {
				memberPwdNow : value
			};
			
			$.ajax({
				type : method,
				url : action,
				data : data,
				async : false,
				success : function(data) {
					MEMBER_LOG_UTILS.log(data);
					
					if (data.result.code === '200') {
						status.message.checkMemberPassword = data.result.message;
						if (data.result.status === '0') {
							flag = true;
						} else {
							flag = false;
						}
					}
					else if (data.result.code == '204' && data.result.status == '12') {
						alert('비밀번호를 틀리셨습니다. 5회 실패 시 자동 로그아웃 됩니다.');
						window.location = '/logout';
					}
				}
			});
		}
		return flag;
	}

	/**
	 * 콜백을 설정한다.
	 * 
	 * @public
	 * @function
	 * @param {String} name 콜백 이름<br /> - <b>initialized</b>: 초기화시<br /> - <b>completed</b>: 활성화시
	 * @param {Function} handler 핸들러
	 * @throws {Error} 알 수 없는 콜백 이름
	 * @throws {TypeError} Function 타입이 아닌 핸들러
	 * @returns
	 */
	this.setCallback = function(name, handler) {
		if (_.isFunction(handler)) {
			switch (name) {
			case 'initialized':
				callback.initialized = handler;
				break;

			case 'completed':
				callback.completed = handler;
				break;

			default:
				throw new Error('알 수 없는 콜백 이름입니다.');
				break;
			}
		} else {
			throw new TypeError('매개변수가 Function 타입이 아닙니다.');
		}
	};

	var customEvent = (function() {
		var func = {};
		var success = function(formId) {
		};
		var fail = function(formId) {
		};
		var run = function(isCheck, formId) {
			if (isCheck) {
				func.success(formId);
				return;
			}
			func.fail(formId);
		}
		func.run = run;
		func.success = success;
		func.fail = fail;
		return func;
	}());

	/**
	 * 아이디를 검증한다.
	 * 
	 * @public
	 * @function
	 * @throws
	 * @returns
	 */
	var idHeandler = function(event) {
		var text_value = $('#' + id.member.id).val();
		if (_.isEmpty(text_value) === false
				&& _.isEmpty($('#' + id.member.password).val()) === false) {
			// 패스워드 blur 실행
			$('#' + id.member.password).trigger('blur');
		}

		if (isIdValid(text_value)) {
			status.isIdRock = true;
			$('#' + id.member.id).removeClass('red_txt');
			$('#' + id.message.id).removeClass('red_txt');
			$('#' + id.message.id).addClass('blue_txt');
			$('#' + id.message.id).parents(".member_error").addClass('member_error_txt2');
			$('#' + id.member.id).parents(".join--id").removeClass('join--error');
		}
		else {
			status.isIdRock = false;
			$('#' + id.member.id).addClass('red_txt');
			$('#' + id.message.id).removeClass('blue_txt');
			$('#' + id.message.id).addClass('red_txt');
			$('#' + id.message.id).parents(".member_error").removeClass('member_error_txt2');
			$('#' + id.member.id).parents(".join--id").addClass('join--error');
		}

		$('#' + id.message.id).html(status.message.id);
		customEvent.run(isIdValid, id.message.id);
	};

	/**
	 * 패스워드를 검증한다.
	 * 
	 * @public
	 * @function
	 * @throws
	 * @returns
	 */
	var passwordHandler = function(event) {
		// 패스워드확인 blur 실행
		var text_value = $('#' + id.member.password).val();

		// 패스워드확인 blur 실행
		if (_.isEmpty(text_value) === false
				&& _.isEmpty($('#' + id.member.passwordConfirm).val()) === false) {
			$('#' + id.member.passwordConfirm).trigger('blur');
		}

		// 페스워드 검증
		if (isPasswordValid(text_value)) {
			status.isPasswordRock = true;
			$('#' + id.member.password).removeClass('red_txt');
			$('#' + id.member.password).removeClass('modify_error');
			$('#' + id.message.password).removeClass('red_txt');
			$('#' + id.message.password).addClass('blue_txt');
			$('#' + id.message.password).parents(".member_error").addClass('member_error_txt2');
			$('#' + id.member.password).parents(".join--pwd").removeClass('join--error');
		} else {
			status.isPasswordRock = false;
			$('#' + id.member.password).addClass('red_txt');
			$('#' + id.member.password).addClass('modify_error');
			$('#' + id.message.password).removeClass('blue_txt');
			$('#' + id.message.password).addClass('red_txt');
			$('#' + id.message.password).parents(".member_error").removeClass('member_error_txt2');
			$('#' + id.member.password).parents(".join--pwd").addClass('join--error');
		}

		$('#' + id.message.password).html(status.message.password);
		customEvent.run(isPasswordValid, id.member.password);
	};

	/**
	 * 패스워드 확인을 검증한다.
	 * 
	 * @public
	 * @function
	 * @throws
	 * @returns
	 */
	var passwordConfirmHandler = function(event) {
		var text_value = $('#' + id.member.passwordConfirm).val();

		// 패스워드 확인 검증
		if (isPasswordConfirmValid(text_value)) {
			status.isPasswordConfirmRock = true;
			$('#' + id.member.passwordConfirm).removeClass('red_txt');
			$('#' + id.member.passwordConfirm).removeClass('modify_error');
			$('#' + id.message.passwordConfirm).removeClass('red_txt');
			$('#' + id.message.passwordConfirm).addClass('blue_txt');
			$('#' + id.message.passwordConfirm).parents(".member_error").addClass('member_error_txt2');
			$('#' + id.member.passwordConfirm).parents(".input_wrap").removeClass('error');
			$('#' + id.member.passwordConfirm).parents(".join--pwdCk").removeClass('join--error');
		} else {
			status.isPasswordConfirmRock = false;
			$('#' + id.member.passwordConfirm).addClass('red_txt');
			$('#' + id.member.passwordConfirm).addClass('modify_error');
			$('#' + id.message.passwordConfirm).removeClass('blue_txt');
			$('#' + id.message.passwordConfirm).addClass('red_txt');
			$('#' + id.message.passwordConfirm).parents(".member_error").removeClass('member_error_txt2');
			$('#' + id.member.passwordConfirm).parents(".input_wrap").addClass('error');
			$('#' + id.member.passwordConfirm).parents(".join--pwdCk").addClass('join--error');
		}

		$('#' + id.message.passwordConfirm).html(status.message.passwordConfirm);
		customEvent.run(isPasswordConfirmValid, id.member.passwordConfirm);
	};
	
	/**
	 * 이름을 검증한다.
	 * 
	 * @public
	 * @function
	 * @throws
	 * @returns
	 */
	var nameHandler = function(event) {
		var text_value = $('#' + id.member.name).val();

		// 패스워드 확인 검증
		if (isNameValid(text_value)) {
			status.isNameRock = true;
			$('#' + id.member.name).removeClass('red_txt');
			$('#' + id.message.name).removeClass('red_txt');
			$('#' + id.message.name).addClass('blue_txt');
			$('#' + id.message.name).parents(".member_error").addClass('member_error_txt2');
			$('#' + id.member.name).parents(".join--name").removeClass('join--error');
		} else {
			status.isNameRock = false;
			$('#' + id.member.name).addClass('red_txt');
			$('#' + id.message.name).removeClass('blue_txt');
			$('#' + id.message.name).addClass('red_txt');
			$('#' + id.message.name).parents(".member_error").removeClass('member_error_txt2');
			$('#' + id.member.name).parents(".join--name").addClass('join--error');
		}

		$('#' + id.message.name).html(status.message.name);
		customEvent.run(isNameValid, id.member.name);
	};
	
	/**
	 * 닉네임을 검증한다.
	 * 
	 * @public
	 * @function
	 * @throws
	 * @returns
	 */
	var nicknameHandler = function(event) {
		var text_value = $('#' + id.member.nickname).val();

		// 닉네임 검증되었다면
		if (isNicknameValid(text_value)) {
			status.isNicknameRock = true;
			$('#' + id.member.nickname).removeClass('red_txt');
			$('#' + id.member.nickname).removeClass('modify_error');
			$('#' + id.message.nickname).removeClass('red_txt');
			$('#' + id.message.nickname).addClass('blue_txt');
			$('#' + id.message.nickname).parents(".member_error").addClass('member_error_txt2');
			$('#' + id.member.nickname).parents(".join--nickname2").removeClass('join--error');
			$('#' + id.member.nickname).parents(".join--nickname").removeClass('join--error');
		}
		// text 가 검증되지 않았다면
		else {
			status.isNicknameRock = false;
			$('#' + id.member.nickname).addClass('red_txt');
			$('#' + id.member.nickname).addClass('modify_error');
			$('#' + id.message.nickname).removeClass('blue_txt');
			$('#' + id.message.nickname).addClass('red_txt');
			$('#' + id.message.nickname).parents(".member_error").removeClass('member_error_txt2');
			$('#' + id.member.nickname).parents(".join--nickname2").addClass('join--error');
			$('#' + id.member.nickname).parents(".join--nickname").addClass('join--error');
		}

		$('#' + id.message.nickname).html(status.message.nickname);
		customEvent.run(isNicknameValid, id.member.nickname);
	};

	/**
	 * 이메일을 검증한다.
	 * 
	 * @public
	 * @function
	 * @throws
	 * @returns
	 */
	var emailHandler = function(event) {
		// 이메일 필드값에 따른 분리
		var text_value = "";
		var emailFeildFlag = 0;
		
		if (!_.isEmpty (id.member.email.emailFirst) && _.isEmpty (id.member.email.emailSecond)) {
			emailFeildFlag =1;
			text_value = $('#'+id.member.email.emailFirst).val();
		}
		
		if (!_.isEmpty (id.member.email.emailFirst) && !_.isEmpty (id.member.email.emailSecond)) {
			emailFeildFlag = 2;
			text_value = $('#'+id.member.email.emailFirst).val() + "@" + $('#'+ id.member.email.emailSecond).val();
		}
		
		if(emailFeildFlag == 1) {
			// 이메일 검증되었다면
			if (isEmailValid(text_value)) {
				status.isEmailRock = true;

				$('#' + id.member.email.emailFirst).removeClass('red_txt');
				$('#' + id.member.email.emailFirst).removeClass('modify_error');
				$('#' + id.message.email).removeClass('red_txt');
				$('#' + id.message.email).addClass('blue_txt');
				$('#' + id.message.email).parents(".member_error").addClass('member_error_txt2');
				$('#' + id.member.email.emailFirst).parents(".join--email").removeClass('join--error');
			}
			// text 가 검증되지 않았다면
			else {
				status.isEmailRock = false;
				$('#' + id.member.email.emailFirst).addClass('red_txt');
				$('#' + id.member.email.emailFirst).addClass('modify_error');
				$('#' + id.message.email).removeClass('blue_txt');
				$('#' + id.message.email).addClass('red_txt');
				$('#' + id.message.email).addClass('red_txt');
				$('#' + id.message.email).parents(".member_error").removeClass('member_error_txt2');
				$('#' + id.member.email.emailFirst).parents(".join--email").addClass('join--error');
			}
			$('#' + id.message.email).html(status.message.email);
			customEvent.run(isEmailValid, id.member.email.emailFirst);
		}
		else if (emailFeildFlag == 2) {
			// 이메일 검증되었다면
			if (isEmailValid(text_value)) {
				status.isEmailRock = true;

				$('#' + id.member.email.emailFirst).removeClass('red_txt');
				$('#' + id.member.email.emailSecond).removeClass('red_txt');
				$('#' + id.message.email).removeClass('red_txt');
				$('#' + id.message.email).addClass('blue_txt');
				$('#' + id.message.email).parents(".member_error").addClass('member_error_txt2');
			}
			// text 가 검증되지 않았다면
			else {
				status.isEmailRock = false;
				$('#' + id.member.email.emailFirst).addClass('red_txt');
				$('#' + id.member.email.emailSecond).addClass('red_txt');
				$('#' + id.message.email).removeClass('blue_txt');
				$('#' + id.message.email).addClass('red_txt');
				$('#' + id.message.email).parents(".member_error").removeClass('member_error_txt2');
			}
			
			$('#' + id.message.email).html(status.message.email);
			customEvent.run(isEmailValid, id.member.email.emailSecond);
		}
		else {
			customEvent.run(isEmailValid, id.member.email.emailFirst);
		}
	}
	
	/**
	 * 아이디 + 패스워드 일치 여부 검증
	 */
	var checkMemberPasswordHandler = function(event) {
		var text_value = $('#' + id.member.nowPassword).val();
		
		// 아이디 패스워드가 검증되었다
		if (isCheckMembmerPwdValid(text_value)) {
			status.isCheckMemberPasswordRock = true;

			$('#' + id.member.nowPassword).removeClass('red_txt');
			$('#' + id.member.nowPassword).removeClass('modify_error');
			$('#' + id.message.nowPassword).removeClass('red_txt');
			$('#' + id.message.nowPassword).parents(".member_error").addClass('member_error_txt2');
		}
		// text 가 검증되지 않았다면
		else {
			status.isCheckMemberPasswordRock = false;

			$('#' + id.member.nowPassword).addClass('red_txt');
			$('#' + id.member.nowPassword).addClass('modify_error');
			$('#' + id.message.nowPassword).addClass('red_txt');
			$('#' + id.message.nowPassword).parents(".member_error").removeClass('member_error_txt2');
		}
		
		$('#' + id.message.nowPassword).html(status.message.checkMemberPassword);
		customEvent.run(isCheckMembmerPwdValid, id.member.nowPassword);
	};
	
	var checkValidationStatus = function() {
		if($('#danawa-member-modify-passwordValidation .on').length == 4) {
			return true;
		}
		alert("비밀번호 설정 필수 조건을 확인하시기 바랍니다.");
		return false;
	}
}