import { BASE_URL, HOME_URL } from "../js/url.js";
jQ(document).ready(function(){
    let expirationTime =  2 * 60 * 1000;
    updateTimer();
    jQ('#timer').on('click',function(){
        resetTimer();
    });
    function updateTimer(){
          if ( expirationTime <= 0 ) {
              clearInterval(setI)
              jQ('#timer_img').removeClass('timer_hit');
                jQ('#timer_img').addClass('timer_dead');
                jQ('#timer_img').removeClass('timer_def');
                setTimeout(()=>{youOut()},1000)
                  
            }else if (0< expirationTime && expirationTime <= 1000 * 60 ) {
                jQ('#timer_img').addClass('timer_hit');
                jQ('#timer_img').removeClass('timer_dead');
                jQ('#timer_img').removeClass('timer_def');
                expirationTime -= 1000;
                let remainingMilliseconds = expirationTime	                
                let remainingSeconds = Math.floor(remainingMilliseconds / 1000);
                let minutes = Math.floor(remainingSeconds / 60);
                let seconds = remainingSeconds % 60;	                
                let displayTime = minutes.toString() +" : "+ seconds.toString();	                
                jQ('#timer_clock').text(displayTime);
            }
          else if ( expirationTime >= 1000 * 60 ) {
                expirationTime -= 1000;
                let remainingMilliseconds = expirationTime	                
                let remainingSeconds = Math.floor(remainingMilliseconds / 1000);
                let minutes = Math.floor(remainingSeconds / 60);
                let seconds = remainingSeconds % 60;	                
                let displayTime = minutes.toString() +" : "+ seconds.toString();	                
                jQ('#timer_clock').text(displayTime);
                
            }
    }
    function resetTimer() {
        expirationTime = 2 * 60 * 1000; // 5 minutes in milliseconds
        jQ('#timer_img').removeClass('timer_hit');
        jQ('#timer_img').removeClass('timer_dead');
        jQ('#timer_img').addClass('timer_def');
        updateTimer();
    };
        // Update timer status every second
        let setI = setInterval(updateTimer, 1000);
})
function youOut(){		
    let tempData = {
            "p_name" : jQ('#sessionId').val().toString(),
            "ro_lr" : jQ('#sessionNew').val().toString(),
            "l_room" : parseInt(jQ('#sessionLoc').val()),
            "b_type" : parseInt(jQ('#sessionTyp').val())				
    }
    jQ.ajax({
        url : BASE_URL + "/game/outTimer",
        type : 'POST',
        contentType: 'application/json',
        data: JSON.stringify(tempData),
        success: function (data) {
            window.location.href= HOME_URL + `/index`;
        },
        error: function (errorThrown) {
            
        }
    })
    
    
}