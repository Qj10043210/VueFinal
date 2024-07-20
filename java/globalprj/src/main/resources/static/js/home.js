// const contextPath = window.contextPath || '';
const musicPlay01 = new Audio('/res/1.mp3');


jQ(document).on('click','body',function(){
	console.log("Teee")
musicPlay01.loop=false;
musicPlay01.muted=false;
musicPlay01.play()
            	jQ('#main_load').fadeIn();
            	setTimeout(()=>{
            		jQ('#main_load').addClass("done")
            		jQ('#main_load').css("display","flex");
            		jQ('#main_load_msg').fadeIn();	
            	},3050)
            })
           	jQ('#main_load_msg').on('click',function(){
           		window.location.href = "/maple/index";
           	})
