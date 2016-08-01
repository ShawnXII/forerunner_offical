var Login = function () {
    return {     
    	//main function to initiate the module	
        init: function (type) {
        	 jQuery('#back-btn').click(function () {
 	            /*jQuery('.login-form').show();
 	            jQuery('.forget-form').hide();*/
 	        });
        	if(type=='login'){
        		$('#loginForm').validate({
        			errorElement: 'label', //default input error message container
     	            errorClass: 'help-inline', // default input error message class
     	            focusInvalid: false, // do not focus the last invalid input
     	            rules: {
	   	                username: { required: true},
	   	                password: { required: true},
	   	                remember: { required: false}
     	            },
     	           messages: {
	   	                username: {
	   	                    required: "请输入您的用户名/手机号/邮箱."
	   	                },
	   	                password: {
	   	                    required: "请输入您的密码."
	   	                }
     	           },
     	          invalidHandler: function (form, validator) { //display error alert on form submit 
     	        	var errors = validator.numberOfInvalids();
     	        	alert(errors);
     	        	console.log(errors);
  	               /* $('.alert-error', $('#loginForm')).show();*/
  	              },
  	              highlight: function (element) { // hightlight error inputs
	                $(element).closest('.control-group').addClass('error'); // set error class to the control group
	              },
	              success: function (label) {
		              label.closest('.control-group').removeClass('error');
		               label.remove();
		           },
		           errorPlacement: function (error, element) {
		                error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
		            },
		            submitHandler: function (form) {
		            	login();
		            	 //form.submit();
		            }
        		});
        		function login(){
            		$.ajax({
            			url:$('#loginForm').attr('action'),       		
            			type:'POST',
            			async:true,
            			data:$('#loginForm').serialize(),
            			dataType:'JSON',
            			success:function(msg){
            				console.log(msg);
            				var flag=msg.flag;
            				if(flag||flag=='true'){
            					
            				}else{
            					var userErrorMsg=msg.userErrorMsg;
            					var publiErrorMsg=msg.publiErrorMsg;
            					var passwordErrorMsg=msg.passwordErrorMsg;
            					if(passwordErrorMsg!=null){
            						$('<label for="password" class="help-inline help-small no-left-padding">'+passwordErrorMsg+'</label>').appendTo($('#password').parent());
            					}
            					if(publiErrorMsg!=null){
            						$('.alert-error').find('span').text(publiErrorMsg).removeClass('hide');
            					}
            					if(userErrorMsg!=null){
            						$('<label for="username" class="help-inline help-small no-left-padding">'+userErrorMsg+'</label>').appendTo($('#username').parent());
            					}
            				}
            			},
            			error:function(XMLHttpRequest, textStatus, errorThrown){
            				
            			}
            		});
            	}
    	        $('.login-form input').keypress(function (e) {
    	            if (e.which == 13) {
    	                if ($('.login-form').validate().form()) {
    	                	login();
    	                }
    	                return false;
    	            }
    	        });
        	}else if(type=='enroll'){
        		$('.register-form').validate({
    	            errorElement: 'label', //default input error message container
    	            errorClass: 'help-inline', // default input error message class
    	            focusInvalid: false, // do not focus the last invalid input
    	            ignore: "",
    	            rules: {
    	                username: {
    	                    required: true
    	                },
    	                password: {
    	                    required: true
    	                },
    	                rpassword: {
    	                    equalTo: "#register_password"
    	                },
    	                email: {
    	                    required: true,
    	                    email: true
    	                },
    	                tnc: {
    	                    required: true
    	                }
    	            },

    	            messages: { // custom messages for radio buttons and checkboxes
    	                tnc: {
    	                    required: "Please accept TNC first."
    	                }
    	            },

    	            invalidHandler: function (event, validator) { //display error alert on form submit   

    	            },

    	            highlight: function (element) { // hightlight error inputs
    	                $(element)
    	                    .closest('.control-group').addClass('error'); // set error class to the control group
    	            },

    	            success: function (label) {
    	                label.closest('.control-group').removeClass('error');
    	                label.remove();
    	            },

    	            errorPlacement: function (error, element) {
    	                if (element.attr("name") == "tnc") { // insert checkbox errors after the container                  
    	                    error.addClass('help-small no-left-padding').insertAfter($('#register_tnc_error'));
    	                } else {
    	                    error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
    	                }
    	            },

    	            submitHandler: function (form) {
    	                window.location.href = "index.html";
    	            }
    	        });

    	        jQuery('#register-btn').click(function () {
    	            jQuery('.login-form').hide();
    	            jQuery('.register-form').show();
    	        });

    	        jQuery('#register-back-btn').click(function () {
    	            jQuery('.login-form').show();
    	            jQuery('.register-form').hide();
    	        });
        	}    
        }

    };

}();