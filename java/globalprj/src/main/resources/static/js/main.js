import { BASE_URL } from "./url.js";
window.addEventListener("wheel", (e) => e.preventDefault(), { passive: false });
window.addEventListener('mousedown', (e) => {
    if (e.button === 1) {
        e.preventDefault();
    }
});
var expLimit=1000;
var cUrl = BASE_URL + "/client/"
jQ(document).on("click",'#player_icon',function(){
	let pname = jQ('#sessionId').val();
	if(pname != null && pname != ""){
		jQ.ajax({
			url:cUrl+"getUserInfo",
			type:'post',
			data:{pname : pname},
			success:function(data){
				jQ('#box_str_v').text(data[0].pstr);
				jQ('#box_dex_v').text(data[0].pdex);
				jQ('#box_int_v').text(data[0].pint);
				jQ('#box_luk_v').text(data[0].pluk);
				jQ('#box_point_v').text(data[0].ppoint);
				jQ('#box_level_v').text(data[0].plevel);
				let exps = (data[0].pexp / expLimit) * 100;
				jQ('#box_status_bar_exp_b').css({
        		clipPath: `polygon(0% 0%, ${exps}% 0%, ${exps}% 100%, 0% 100%)`
    			})
    			jQ('#player_icon').css('display','none');
    			jQ('#player_box').css('display','flex');
			},
			error: function (errorThrown) {

        	}
		})
	
	}
})

jQ(document).on("mouseleave",'#player_box',function(){
	jQ('#player_icon').css('display','block');
	jQ('#player_box').css('display','none');
});
