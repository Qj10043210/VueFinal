import { BASE_URL, HOME_URL } from "./url.js";
// opening.js
import * as ld from "./load.js";
import * as fc from "./functions.js";


var op_n = -1;
var somecase = false;
const op_ba = document.getElementById('io_m');
const op_ml = document.getElementById('io_m_log');
const op_lb = document.getElementById('io_m_log_btn');
const op_msg = document.getElementById('io_msg');
const op_wrl = document.getElementById('io_world');
const op_wrl_b = document.getElementById('io_world_0');
const op_chars = document.getElementById('io_chars');
const op_mak = document.getElementById('io_making');
var cUrl = BASE_URL + "/client/"
var gUrl = BASE_URL + "/game/"
var jsUrl = "/js/"
var rootUrl = "/"
var resUrl = "/res/"
const musicPlay02 = new Audio(resUrl+'2.mp3');
let music = true;
var loginUrl = cUrl + "login";
var signinUrl = cUrl + "signin";
var signupUrl = cUrl + "signup";
var tocharUrl = cUrl + "tochar";

var hUrl = jsUrl + "hair.json";
var fUrl = jsUrl + "face.json";
var gUrl = jsUrl + "game.json";
var cssUrl = "/css/"

var hairMapSize = 0, faceMapSize = 0, hairMap, faceMap, hairList, faceList, currentHair = 1, currentFace = 1, dice_max = 27, dice_run = false;
var id_check = false, id_temp = "", id_moveOut = false;
var gameMapSize = 0, gameMap;
const l_url = fUrl;
let facejson;
const l_url2 = hUrl;
let hairjson;
const l_url3 = gUrl;
let gamejson;
jQ(document).on('click','body',function(){
	if(music){
	music = false;
	musicPlay02.loop=true;
	musicPlay02.play()
	}
})

ld.jsonData((data1) => {
    facejson = data1;
    ld.jsonData((data2) => {
        hairjson = data2;
        ld.jsonData((data3) => {
            gamejson = data3;

            const uniqueFaceNames = new Set();
            const uniqueHairNames = new Set();
            const uniqueGameNames = new Set();
            const uniqueFaceMaps = new Map();
            const uniqueHairMaps = new Map();
            const uniqueGameMaps = new Map();
            gamejson.forEach((item) => {
                let { g_title } = item;
                if (!uniqueGameNames.has(g_title)) {
                    uniqueGameNames.add(g_title);
                    uniqueGameMaps.set(g_title, item);

                }
            })
            facejson.forEach((item) => {
                let { face_name } = item;
                if (!uniqueFaceNames.has(face_name)) {
                    uniqueFaceNames.add(face_name);
                    uniqueFaceMaps.set(face_name, item);
                }
            })
            hairjson.forEach((item) => {
                let { hair_name } = item;
                if (!uniqueHairNames.has(hair_name)) {
                    uniqueHairNames.add(hair_name);
                    uniqueHairMaps.set(hair_name, item);
                }
            })
            gameMapSize += uniqueGameMaps.size;
            hairMapSize += uniqueHairMaps.size;
            faceMapSize += uniqueFaceMaps.size;
            jQ('#io_making_right_hair').val([...uniqueHairNames][0])
            jQ('#io_making_right_face').val([...uniqueFaceNames][0])
            jQ('#io_making_right_hair').data("hairSize", hairMapSize);
            jQ('#io_making_right_face').data("faceSize", faceMapSize);
            hairMap = uniqueHairMaps;
            faceMap = uniqueFaceMaps;
            hairList = uniqueHairNames;
            faceList = uniqueFaceNames;
            gameMap = uniqueGameMaps;
            let tempN = 0;
            gameMap.forEach((value, key) => {
                let i = tempN
                jQ(`#io_character_loged_icon_${i}`).css({
                    backgroundImage: `url('${cssUrl}game/${value.g_png}.png')`
                });
                jQ(`#io_character_loged_icon_${i}`).data("title", value.g_title);
                console.log(jQ(`#io_character_loged_icon_${i}`).data("title"))
                jQ(`#io_character_loged_icon_${i}`).removeClass('io_character_loged_icon_em');
                tempN++;
            });
        }, l_url3)
    }, l_url2)
}, l_url)

let reqId, reqPwd;
let result = null;
op_lb.addEventListener('click', () => {
    console.log("tesss")
    reqId = jQ('#io_m_log_id').val();
    reqPwd = jQ('#io_m_log_pwd').val();
    if (
        reqId !== "" || reqId !== null ||
        reqPwd !== "" || reqPwd !== null) {
        if (result == null) {
            checkLogin(reqId, reqPwd)
        }

    }
    else {
        jQ('#io_m_log_id').focus();
    }

});
op_msg.addEventListener('click', () => {
    if (result != null && id_moveOut == false) {
        switch (result) {
            case 0:
                jQ('#io_msg').removeClass('io_msg_0');
                requestSignUp(reqId, reqPwd);
                requestSignIn(reqId, reqPwd);
                op_ba.style.backgroundPosition = '0% 60%';
                op_ml.style.display = `none`;
                op_wrl.style.display = `block`;
                break;
            case 1:
                break;
            case 2:
            case 3:
                jQ('#io_m_log_id').val("");
                jQ('#io_m_log_pwd').val("");
                jQ('#io_msg').removeClass('io_msg_2');
                break;
        }
        result = null;
        jQ('#io_msg').fadeOut(300);

    } else if (id_moveOut) {
        jQ('#io_msg').removeClass('io_msg_0');
        jQ('#io_msg').removeClass('io_msg_1');
        jQ('#io_msg').removeClass('io_msg_2');
        jQ('#io_msg').fadeOut(300);
    }
});
op_wrl_b.addEventListener('click', () => {
    jQ.ajax({
        url: tocharUrl,
        type: 'GET',

        success: function (data) {
            op_ba.style.backgroundPosition = '0% 31%';
            op_wrl.style.display = `none`;
            op_chars.style.display = `block`;
            let lists = data;            
            if (lists.length > 0) {
                for (let i = 0; i < lists.length; i++) {
                    let hairSource = fc.findAndMakeMap("hair_original", lists[i].phair, hairMap);
                    let faceSource = fc.findAndMakeMap("face_original", lists[i].pface, faceMap);                    
                    jQ(`#io_chars_${i}`).removeClass('co_chars_em');
                    jQ(`#io_chars_${i}`).data("name", lists[i].pname)
                    jQ(`#io_chars_${i}`).css({
                        aspectRatio: `59/78`,
                        backgroundImage: `url('${cssUrl}msg/body.gif')`,
                    });
                    jQ(`#io_chars_head_${i}`).css({
                        backgroundImage: `url('${cssUrl}msg/head.png')`,
                    });
                    jQ(`#io_chars_hair_${i}`).css({
                        width: `calc(100% * (${hairSource[1].hair_w} / 57))`,
                        aspectRatio: `${hairSource[1].hair_w} / ${hairSource[1].hair_h}`,
                        top: `calc(100% * (${hairSource[1].hair_y} / 50))`,
                        left: `calc(100% * (${hairSource[1].hair_x} / 57))`,
                        backgroundImage: `url('${cssUrl}hair/${hairSource[1].hair_number}.png')`
                    });
                    jQ(`#io_chars_face_${i}`).css({

                        aspectRatio: `${faceSource[1].face_x} / ${faceSource[1].face_y}`,
                        backgroundImage: `url('${cssUrl}face/${faceSource[1].face_number}.png')`
                    });

                }
            } else {

            }
        },
        error: function (errorThrown) {
            op_ba.style.backgroundPosition = '0% 95%';
            op_wrl.style.display = `none`;
            id_moveOut = false;
        }

    });
})
jQ(document).on("click", '.co_chars_def', function () {
    if (jQ(this).hasClass('co_chars_em')) {
        op_chars.style.display = `none`;
        op_ba.style.backgroundPosition = '0% -1%';
        op_mak.style.display = `block`;
        id_moveOut = true;
    } else {

    }
});


function requestSignUp(reqId, reqPwd) {
    jQ.ajax({
        url: signupUrl,
        type: 'POST',
        data: { reqId: reqId, reqPwd: reqPwd },
        success: function (data) {

        },
        error: function (errorThrown) {
            location.reload(true);
        }

    });
}
function requestSignIn(reqId, reqPwd) {
    jQ.ajax({
        url: signinUrl,
        type: 'POST',
        data: { reqId: reqId, reqPwd: reqPwd },
        success: function (data) {

        },
        error: function (errorThrown) {
            location.reload(true);
        }

    });
}
function checkLogin(reqId, reqPwd) {
    jQ.ajax({
        url: loginUrl,
        type: 'POST',
        data: { reqId: reqId, reqPwd: reqPwd },
        success: function (data) {
            result = parseInt(data);
            excuteLogin(result);
        },
        error: function (errorThrown) {
            location.reload(true);
        }

    });

};
function excuteLogin(result) {
    switch (result) {
        case 0:
            jQ('#io_msg').addClass('io_msg_0');
            jQ('#io_msg').fadeIn(300);
            break;
        case 1:
            requestSignIn(reqId, reqPwd);
            op_ba.style.backgroundPosition = '0% 60%';
            op_ml.style.display = `none`;
            op_wrl.style.display = `block`;
            break;
        case 2:
        case 3:
            jQ('#io_msg').addClass('io_msg_2');
            jQ('#io_msg').fadeIn(300);
            break;
    }

}

jQ(document).on('click', '.io_making_right_input', function () {
    let tempId = jQ(this).attr('id');
    let tempData = 0;
    switch (tempId) {
        case 'io_making_right_hair':
            tempData = jQ('#io_making_right_hair').data('hairSize');
            if (currentHair < tempData) {
                jQ('#io_making_right_hair').val([...hairList][currentHair]);
                jQ('#io_making_middle_hair').css({
                    width: `calc(100% * (${(hairMap.get([...hairList][currentHair])).hair_w} / 57))`,
                    aspectRatio: `${(hairMap.get([...hairList][currentHair])).hair_w} / ${(hairMap.get([...hairList][currentHair])).hair_h}`,
                    top: `calc(100% * (${(hairMap.get([...hairList][currentHair])).hair_y} / 50))`,
                    left: `calc(100% * (${(hairMap.get([...hairList][currentHair])).hair_x} / 57))`,
                    backgroundImage: `url('${cssUrl}hair/${(hairMap.get([...hairList][currentHair])).hair_number}.png')`
                })
                currentHair++;
            } else if (currentHair == tempData) {
                jQ('#io_making_right_hair').val([...hairList][0]);
                currentHair = 0;
                jQ('#io_making_middle_hair').css({
                    width: `calc(100% * (${(hairMap.get([...hairList][currentHair])).hair_w} / 57))`,
                    aspectRatio: `${(hairMap.get([...hairList][currentHair])).hair_w} / ${(hairMap.get([...hairList][currentHair])).hair_h}`,
                    top: `calc(100% * (${(hairMap.get([...hairList][currentHair])).hair_y} / 50))`,
                    left: `calc(100% * (${(hairMap.get([...hairList][currentHair])).hair_x} / 57))`,
                    backgroundImage: `url('${cssUrl}hair/${(hairMap.get([...hairList][currentHair])).hair_number}.png')`
                })
            }
            break;
        case 'io_making_right_face':
            tempData = jQ('#io_making_right_face').data('faceSize');
            if (currentFace < tempData) {
                jQ('#io_making_right_face').val([...faceList][currentFace]);
                jQ('#io_making_middle_face').css({

                    aspectRatio: `${(faceMap.get([...faceList][currentFace])).face_x} / ${(faceMap.get([...faceList][currentFace])).face_y}`,
                    backgroundImage: `url('${cssUrl}face/${(faceMap.get([...faceList][currentFace])).face_number}.png')`
                })
                currentFace++;
            } else if (currentFace == tempData) {
                jQ('#io_making_right_face').val([...faceList][0]);
                currentFace = 0;
                jQ('#io_making_middle_face').css({

                    aspectRatio: `${(faceMap.get([...faceList][currentFace])).face_x} / ${(faceMap.get([...faceList][currentFace])).face_y}`,
                    backgroundImage: `url('${cssUrl}face/${(faceMap.get([...faceList][currentFace])).face_number}.png')`
                })
            }
            break;
        case 'io_making_right_weapon':
            break;
        default:
            break;

    }
})



jQ(document).on('click', '#io_making_left_dice', function () {
    if (dice_run) {

        setTimeout(function () {
            dice_run = false;
        }, 100)
    } else {
        jQ(this).css({
            backgroundImage: `url('${cssUrl}msg/dice.gif')`,
            aspectRatio: `37 / 56`
        });
        let randN1, randN2, randN3, randN4;
        randN1 = fc.functions_RandomStartEndSize(4, 12, 100);
        if (randN1 == 4) {
            randN2 = fc.functions_RandomStartEndSize(4, 12, 100);
        } else {
            randN2 = fc.functions_RandomStartEndSize(4, dice_max - randN1 - 8, 100);
        }
        randN3 = fc.functions_RandomStartEndSize(4, dice_max - randN1 - randN2 - 4, 100);
        randN4 = fc.functions_RandomStartEndSize(4, dice_max - randN1 - randN2 - randN3, 100);
        let tempList = [];
        tempList.push(randN1)
        tempList.push(randN2)
        tempList.push(randN3)
        tempList.push(randN4)
        fc.shuffleArray(tempList)
        jQ('#io_making_left_str').val(tempList[0]);
        jQ('#io_making_left_dex').val(tempList[1]);
        jQ('#io_making_left_int').val(tempList[2]);
        jQ('#io_making_left_luk').val(tempList[3]);
        setTimeout(function () {
            console.log("why?")
            dice_run = true;
            jQ('#io_making_left_dice').css({
                backgroundImage: `url('${cssUrl}msg/dice.png')`,
                aspectRatio: `37 / 26`
            });
        }, 800)
    }


})

jQ(document).on("click", '#io_making_right_check', function () {
    let tempS = jQ('#io_making_right_name').val();
    if (tempS != "" && tempS != null) {
        jQ.ajax({
            url: cUrl + "checkName",
            type: 'POST',
            data: { reqName: tempS },
            success: function (data) {
                if (data == "1") {
                    id_check = false;
                    jQ('#io_msg').addClass('io_msg_1');
                    jQ('#io_msg').fadeIn(300);
                } else if (data == "0") {
                    jQ('#io_msg').addClass('io_msg_0');
                    jQ('#io_msg').fadeIn(300);
                    id_check = true;
                    id_temp = tempS;
                }
            },
            error: function (errorThrown) {
                jQ('#io_msg').addClass('io_msg_1');
                jQ('#io_msg').fadeIn(300);
                id_check = false;
            }

        });
    } else {
        id_check = false;
        jQ('#io_msg').addClass('io_msg_1');
        jQ('#io_msg').fadeIn(300);
    }
})

jQ(document).on("click", '#io_making_right_cancel', function () {
    allOut()
})
jQ(document).on("click", '#io_making_right_confirm', function () {
    let tempS = jQ('#io_making_right_name').val();
    if (tempS != id_temp) {
        jQ('#io_msg').addClass('io_msg_1');
        jQ('#io_msg').fadeIn(300);
    } else if (id_check && tempS == id_temp) {
        if (currentFace != 0) {
            currentFace--;
        }
        if (currentHair != 0) {
            currentHair--;
        }
        let datas = {
            pname: jQ('#io_making_right_name').val(),
            pface: parseInt(faceMap.get([...faceList][currentFace]).face_original),
            phair: parseInt(hairMap.get([...hairList][currentHair]).hair_original),
            pstr: parseInt(jQ('#io_making_left_str').val()),
            pdex: parseInt(jQ('#io_making_left_dex').val()),
            pint: parseInt(jQ('#io_making_left_int').val()),
            pluk: parseInt(jQ('#io_making_left_luk').val())
        }
        console.log(datas)
        jQ.ajax({
            url: cUrl + "postPlayer",
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(datas),
            success: function (data) {
                console.log("성공",data)
                allOut()
            },
            error: function (errorThrown) {
                jQ('#io_msg').addClass('io_msg_1');
                jQ('#io_msg').fadeIn(300);
            }

        })
    }
})
let mouseWhere = true;
jQ(document).on('click', '.co_chars_def', function () {
    if (jQ(this).hasClass('co_chars_em')) { } else {
        jQ('#io_character_loged').data("name", jQ(this).data("name"))
        jQ('#io_character_loged').data("number", jQ(this).index())
        jQ('#io_character_loged').fadeIn(100)
    }
})
jQ(document).on('mouseleave', '#io_character_loged', function () {
    if (mouseWhere) {
        jQ(this).fadeOut(100)
        jQ('#io_character_loged').data("name", "")
        jQ('#io_character_loged').data("number", "")
    } else {
        mouseWhere = true;
    }

})
jQ(document).on('mouseleave', '#io_character_loged_nope', function () {
    mouseWhere = false;
    jQ(this).fadeOut(100)
})

jQ(document).on('click', '#io_character_loged_out', function () {
    goodByeMyC(jQ('#io_character_loged').data("name"), jQ('#io_character_loged').data("number"))
    jQ('#io_character_loged').fadeOut(400)
})
jQ(document).on('click', '.io_character_loged_icon', function () {
    if (jQ(this).hasClass('io_character_loged_icon_em')) {
        jQ('#io_character_loged_nope').fadeIn(150);
    } else {
    	jQ('#globalLoad').css('display','block');
        let temps = jQ(this).data("title");
        let names = jQ('#io_character_loged').data("name");
        op_ba.style.backgroundPosition = '0% 95%';
        op_mak.style.display = `none`;
        op_msg.style.display = `none`;
        op_ml.style.display = `block`;
        currentFace = 1;
        currentHair = 1;
        jQ('#io_m_log_id').val("");
        jQ('#io_m_log_pwd').val("");
        jQ('#io_making_right_name').val("");
        jQ('#io_making_left_str').val(1);
        jQ('#io_making_left_dex').val(1);
        jQ('#io_making_left_int').val(1);
        jQ('#io_making_left_luk').val(1);
        jQ('#io_making_middle_face').css({
            width: 'calc(100% * 26 / 57)',
            position: 'absolute',
            aspectRatio: '26/16',
            top: 'calc(100% * 30 / 50)',
            left: 'calc(100% * 12 / 57)',
            backgroundSize: 'contain',
            backgroundRepeat: 'no-repeat',
            backgroundImage: `url('${cssUrl}face/0000.png')`
        });
        jQ('#io_making_middle_hair').css({
            width: 'calc(100% * 38 / 57)',
            position: 'absolute',
            aspectRatio: '38/22',
            top: 'calc(100% * 14 / 50)',
            left: 'calc(100% * 9 / 57)',
            backgroundSize: 'contain',
            backgroundRepeat: 'no-repeat',
            backgroundImage: `url('${cssUrl}hair/0000.png')`
        });
        jQ('#io_making_right_hair').val([...hairList][0])
        jQ('#io_making_right_face').val([...faceList][0])
        id_moveOut = false;
        result = null;
        jQ('#globalLoad').fadeOut(1);
        window.location.href = HOME_URL + `/gameopen?data=${encodeURIComponent(temps)}&names=${encodeURIComponent(names)}`;
    }
})



















function goodByeMyC(name, nub) {
    jQ.ajax({
        url: cUrl + "goodbye",
        type: 'POST',
        data: { reqName: name },
        success: function (data) {
            if (data == "q") {
                jQ('#io_character_loged').data("name", "")
                jQ('#io_character_loged').data("number", "")
                jQ('#io_character_loged').fadeOut(100);
                jQ(`#io_chars_${nub}`).css("backgroundImage", `url('${cssUrl}msg/char.png')`);
                jQ(`#io_chars_head_${nub}`).css("backgroundImage", "none")
                jQ(`#io_chars_hair_${nub}`).css("backgroundImage", "none")
                jQ(`#io_chars_face_${nub}`).css("backgroundImage", "none")
                jQ(`#io_chars_${nub}`).addClass('co_chars_em')
            } else {
                jQ('#io_msg').addClass('io_msg_1');
                jQ('#io_msg').fadeIn(300);
                id_check = false;
                jQ('#io_character_loged').data("name", "")
                jQ('#io_character_loged').fadeOut(100)
            }
        },
        error: function (errorThrown) {
            jQ('#io_msg').addClass('io_msg_1');
            jQ('#io_msg').fadeIn(300);
            id_check = false;
            jQ('#io_character_loged').data("name", "")
            jQ('#io_character_loged').fadeOut(100)
        }

    });
}



function allOut() {
    op_ba.style.backgroundPosition = '0% 95%';
    op_mak.style.display = `none`;
    op_msg.style.display = `none`;
    op_ml.style.display = `block`;
    currentFace = 1;
    currentHair = 1;
    jQ('#io_m_log_id').val("");
    jQ('#io_m_log_pwd').val("");
    jQ('#io_making_right_name').val("");
    jQ('#io_making_left_str').val(1);
    jQ('#io_making_left_dex').val(1);
    jQ('#io_making_left_int').val(1);
    jQ('#io_making_left_luk').val(1);
    jQ('#io_making_middle_face').css({
        width: 'calc(100% * 26 / 57)',
        position: 'absolute',
        aspectRatio: '26/16',
        top: 'calc(100% * 30 / 50)',
        left: 'calc(100% * 12 / 57)',
        backgroundSize: 'contain',
        backgroundRepeat: 'no-repeat',
        backgroundImage: `url('${cssUrl}face/0000.png')`
    });
    jQ('#io_making_middle_hair').css({
        width: 'calc(100% * 38 / 57)',
        position: 'absolute',
        aspectRatio: '38/22',
        top: 'calc(100% * 14 / 50)',
        left: 'calc(100% * 9 / 57)',
        backgroundSize: 'contain',
        backgroundRepeat: 'no-repeat',
        backgroundImage: `url('${cssUrl}hair/0000.png')`
    });
    jQ('#io_making_right_hair').val([...hairList][0])
    jQ('#io_making_right_face').val([...faceList][0])
    id_moveOut = false;
    result = null;
    sessionStorage.clear();

}



if (somecase) {
    op_lb.addEventListener('click', () => {
        //make op_n for each case

        switch (op_n) {
            case -1:
                op_ba.style.backgroundPosition = '0% 60%';
                op_ml.style.display = `none`;
                break;
            case 0:
                op_ba.style.backgroundPosition = '0% 0%';
                break;
            case 1:
                op_ba.style.backgroundPosition = '0% 31%';
                break;
            case 2:
                backgroundDiv.style.backgroundPosition = '0% 95%';
                number = -1;
                break;
        }
        op_n++;
        window.scrollTo({
            top: number > 0 ? 0 : document.body.scrollHeight,
            behavior: 'smooth'
        });
    })

}