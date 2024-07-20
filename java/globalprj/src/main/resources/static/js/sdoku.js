import { BASE_URL } from "./url.js";
let times = false;
let saveBoxTemp = [];
let saveBox = [];
let ho = [];
let vo = [];
let bo = [];
let test = false;
let random = 0;
let textBox = "";
let textOpen = "<span class=";
let textClose = "</span>";
let tor = 0;
let showOption = 0;
let howmuchweMissed = 0;
let howmuchweWrong = 0;
let youdidntnoting = 0;
for (let i = 1; i < 10; i++) {
    ho.push([]);
    vo.push([]);
    bo.push([]);
}
var resUrl = "/res/"
const musicPlay05 = new Audio(resUrl+'5.mp3');
let music = true;
$(document).on('click','body',function(){
	if(music){
	music = false;
	musicPlay05.loop=true;
	musicPlay05.play()
	}
})
window.onload = function () {
    $('#smoothCover').delay(100).fadeIn()
    setTimeout(function () {
        $('#coverAll').removeClass("onon")
    }, 4100)
    $('#smoothCover').delay(4220).fadeOut(800)
}

$(document).on("click", "#test", function () {
   doA();

});
function doA(){
    $('#backbox').css({
        backgroundImage: 'none'
    })
    $('#turnbox').css({
        animationDirection: 'reverse'
    })

    $('#coverAll').addClass("onon2");

    doC();
    doB();
}
function doB() {
    if ($('#title').text() !== "") {
        ho = [];
        vo = [];
        bo = [];
        for (let o = 1; o < 10; o++) {
            ho.push([]);
            vo.push([]);
            bo.push([]);
        }
        doResetAll();
    }
    else {
        doResetAll();

    }

    doPick();
    giveAction($('#test'));
    doC();
}
function doC() {

    setTimeout(() => {
        $('#coverAll').removeClass("onon2");
    }, 3200)
}
function doRandom1() {
    doRandom2();
    while (test) {
        doRandom2();
    }
}
function doRandom2() {
    random = Math.random() * 10;
    random = Math.floor(random);
    if (random == 0) {
        test = true;
    } else {
        test = false;
    }
}
function doPick() {

    for (let i = 1; i < 82; i++) {
        let vertical = i % 9;
        let horizen = Math.floor((i - 1) / 9);
        let whichBox = 0;
        switch (vertical) {
            case 0:
                switch (horizen) {
                    case 0:
                    case 1:
                    case 2:
                        whichBox = 2;
                        break;
                    case 3:
                    case 4:
                    case 5:
                        whichBox = 5;
                        break;
                    case 6:
                    case 7:
                    case 8:
                        whichBox = 8;
                        break;
                }
                break;
            case 1:
            case 2:
            case 3:
                switch (horizen) {
                    case 0:
                    case 1:
                    case 2:
                        whichBox = 0;
                        break;
                    case 3:
                    case 4:
                    case 5:
                        whichBox = 3;
                        break;
                    case 6:
                    case 7:
                    case 8:
                        whichBox = 6;
                        break;
                }
                break;
            case 4:
            case 5:
            case 6:
                switch (horizen) {
                    case 0:
                    case 1:
                    case 2:
                        whichBox = 1;
                        break;
                    case 3:
                    case 4:
                    case 5:
                        whichBox = 4;
                        break;
                    case 6:
                    case 7:
                    case 8:
                        whichBox = 7;
                        break;
                }
                break;
            case 7:
            case 8:
                switch (horizen) {
                    case 0:
                    case 1:
                    case 2:
                        whichBox = 2;
                        break;
                    case 3:
                    case 4:
                    case 5:
                        whichBox = 5;
                        break;
                    case 6:
                    case 7:
                    case 8:
                        whichBox = 8;
                        break;
                }
                break;
        }
        let indexB2 = bo[whichBox];
        let indexV2 = vo[vertical];
        let indexH2 = ho[horizen];
        let indexB = 1;
        let indexV = 1;
        let indexH = 1;
        doRandom1();
        let whatweneed = parseInt(random);

        indexH = indexH2.findIndex((num) => num == whatweneed);
        indexV = indexV2.findIndex((num) => num == whatweneed);
        indexB = indexB2.findIndex((num) => num == whatweneed);
        let testF = false;
        if (indexH == -1 && indexV == -1 && indexB == -1) {
            testF = false;
            tor = 0;
        } else {
            testF = true;
            while (testF) {
                if (tor < 1000) {
                    doRandom1();
                    whatweneed = parseInt(random);
                    indexH = indexH2.findIndex((num) => num == whatweneed);
                    indexV = indexV2.findIndex((num) => num == whatweneed);
                    indexB = indexB2.findIndex((num) => num == whatweneed);
                    testF = true;
                    tor += 1
                    if (indexH == -1 && indexV == -1 && indexB == -1) {
                        testF = false;
                        tor = 0;
                        break;
                    }
                }
                else {

                    break;
                }
            }
        }

        if (tor >= 1000) {

            tor = 0;
            ho = [];
            vo = [];
            bo = [];
            for (let o = 1; o < 10; o++) {
                ho.push([]);
                vo.push([]);
                bo.push([]);
            }
            doReset(i);
            break;
        } else {
            ho[horizen].push(whatweneed);
            vo[vertical].push(whatweneed);
            bo[whichBox].push(whatweneed)

            textBox = textOpen + "'" +"t-"+ whatweneed +" "+whatweneed + " showing'>" + whatweneed + textClose + `<input type="text" name="0" value="" oninput="this.value = this.value.replace(/[^1-9.]/g, '').replace(/(\..*)\./g, '$1');" />`;
            $(`.T${i}`).append(textBox);
        }
        // console.log(bo[whichBox])
        // console.log(indexB2)
        // $(`.T${i}`).text(whatweneed)

    }
    // 

    resetway()

}
function doReset(i) {
    for (let o = 1; o < i; o++) {
        $(`.T${o}`).text("");
    }
    howmuchweMissed = 0;
    resetClass()
    doPick()
}
function doResetAll() {
    for (let o = 1; o < 82; o++) {
        $(`.T${o}`).text("");
    }
    howmuchweMissed = 0;
    resetClass()
    $('#title').text("")

}
$(document).on("click", "#save", function () {
    if ($('#title').text() !== "") {

        if (saveBox.length <= 4) {
            let temp = [];
            temp.push(ho);
            temp.push(vo);
            temp.push(bo);
            saveBox.push(temp);

            let a = addElement(saveBox[saveBox.length - 1][0][0], saveBox[saveBox.length - 1][0][1]);
            let temp2 = `<li><span class="list">${a}</span><span class="listDel">delete</span></li>`
            $("#saveBoxUl").append(temp2)

        }
        else {
            $("#saveBoxUl li:last-child").remove();
            let temp = [];
            temp.push(ho);
            temp.push(vo);
            temp.push(bo);
            saveBox.splice(0, 1, temp);
            let a = addElement(saveBox[0][0][0], saveBox[0][1][1]);
            let temp2 = `<li><span class="list">${a}</span><span class="listDel">delete</span></li>`
            $("#saveBoxUl").prepend(temp2)


        }
        // $('#showbox').text("▽▽save complete▽▽")
        // showing('#showbox');

        $('#showbox').addClass("tt")
        setTimeout(() => { $('#showbox').removeClass("tt") }, 1100)



    }
    else {
        console.log("no")
    }
})
$(document).on("click", 'td span', function () {
    let a = [1, 2, 3, 4, 5, 6, 7, 8, 9];
    for (let i = 0; i < a.length; i++) {
        let b = $(this).text();
        if (b == "") {
            $('td').removeClass("focusing");
        }
        if (b == a[i]) {
            $('td').removeClass("focusing");
            $(`.${b}.showing`).parent().addClass("focusing");
            $(`input[type='text'][name='${b}']`).parent().addClass("focusing");
            let c = $(this).parent().attr("class");
            showingYou(c, showOption);
        }
    }

})
$(document).on("click", 'td input', function () {
    let a = [1, 2, 3, 4, 5, 6, 7, 8, 9];
    for (let i = 0; i < a.length; i++) {
        let b = $(this).val();
        if (b == "") {
            $('td').removeClass("focusing");
            let c = $(this).parent().attr("class");
            showingYou(c, showOption);
        }
        if (b == a[i]) {
            $('td').removeClass("focusing");

            $(`.${b}.showing`).parent().addClass("focusing");
            $(`input[type='text'][name='${b}']`).parent().addClass("focusing");
            $(this).parent().addClass("focusing"); //아래다 놓지 않음, 정답일 경우 표시가 안 됨..
            let c = $(this).parent().attr("class");
            showingYou(c, showOption);
        }
    }
})
$(document).on("input", 'td input', function () {
    let a = [1, 2, 3, 4, 5, 6, 7, 8, 9];
    for (let i = 0; i < a.length; i++) {
        let b = $(this).val();
        if (b == "") {
            $('td').removeClass("focusing");
            $(this).attr({
                'name': 0
            });
        }
        if (b == a[i]) {
            $('td').removeClass("focusing");
            $(this).attr({
                'name': b
            });

            $(`.${b}.showing`).parent().addClass("focusing");
            $(`input[type='text'][name='${b}']`).parent().addClass("focusing");
            $(this).parent().addClass("focusing");//아래다 놓지 않음, 정답일 경우 표시가 안 됨..
            let c = $(this).parent().attr("class");
            showingYou(c, showOption);
        }
    }
})

// function showing(arr) {
//     $(arr).fadeIn(1500);
//     $(arr).fadeOut(1500);

// }
$(document).on("click", '.listDel', function () {
    let a = $(this).parent().index();
    // console.log(a)
    saveBox.splice(a, 1);
    $(this).parent().remove();
    console.log(saveBox)

})
$(document).on("click", '.list', function () {
    let a = $(this).parent().index();
    console.log(a)
    doResetAll();
    let b = saveBox[a][0];
    for (let i = 1; i < 82; i++) {
        let c = Math.floor((i - 1) / 9);
        let d = (i - 1) % 9;
        let e = b[c][d]
        textBox = textOpen + "'" + e + " showing'>" + e + textClose + `<input type="text" name="0" value="" oninput="this.value = this.value.replace(/[^1-9.]/g, '').replace(/(\..*)\./g, '$1');" />`;
        $(`.T${i}`).append(textBox);
    }
    resetway()
})


$(document).on("click", "#try", function () {
    if ($('#title').text() !== "") {
        let whatwedeleted = [];
        let howmuchwedelete = doRandom3();
        // let howmuchwedelete = 2;

        for (let i = 0; i < howmuchwedelete; i++) {
            let whatwillbewedelete = doRandom4();
            let checknumber = whatwedeleted.findIndex((num) => num == whatwillbewedelete);
            if (checknumber != -1) {
                let test = true;
                while (test) {
                    let whatwillbewedelete = doRandom4();
                    let checknumber = whatwedeleted.findIndex((num) => num == whatwillbewedelete);
                    test = false;
                    if (checknumber != -1) {
                        test = true;
                        break;
                    }
                }
            } else {
                whatwedeleted.push(whatwillbewedelete);
            }
            $(`.T${whatwillbewedelete} span`).removeClass("showing");
            $(`.T${whatwillbewedelete} input`).addClass("showing");
            resetClass()
        }
    }
    else {
        console.log("no")
    }

});
function doRandom3() {
    let a = Math.random()
    if (a * 10 < 9) {
        a = a * 10 + 1;
    }
    a = Math.floor(Math.log(a) * 100)
    let b = false;
    if (a <= 0 || a > 81) {
        b = true;
        while (b) {
            a = Math.random()
            if (a * 10 < 9) {
                a = a * 10 + 1;
            }
            a = Math.floor(Math.log(a) * 100)
            if (a >= 1 && a <= 81) {
                b = false;
                break;
            }
        }
    }
    return a;
}
function doRandom4() {
    let a = Math.random()
    if (a * 10 < 9) {
        a = a * 10 + 1;
    }
    a = Math.floor(Math.log(a) * 100)
    let b = false;
    if (a <= 0 || a > 81) {
        b = true;
        while (b) {
            a = Math.random()
            if (a * 10 < 9) {
                a = a * 10 + 1;
            }
            a = Math.floor(Math.log(a) * 100)
            if (a >= 1 && a <= 81) {
                b = false;
                break;
            }
        }
    }
    return a;
}
function resetway() {
    $('#title').text("SDOKU ON")
    resetClass()
    saveBoxTemp = [];
    let temp = [];
    temp.push(ho);
    temp.push(vo);
    temp.push(bo);
    saveBoxTemp.push(temp);

    howmuchweMissed = 0;

}
$(document).on("click", "#resetplease", function () {


    doResetAll();
    let b = saveBoxTemp[0][0];
    for (let i = 1; i < 82; i++) {
        let c = Math.floor((i - 1) / 9);
        let d = (i - 1) % 9;
        let e = b[c][d]
        textBox = textOpen + "'" + e + " showing'>" + e + textClose + `<input type="text" name="0" value="" oninput="this.value = this.value.replace(/[^1-9.]/g, '').replace(/(\..*)\./g, '$1');" />`;
        $(`.T${i}`).append(textBox);
    }
    resetway()
})
$(document).on("click", "#retry", function () {
    $('input').val("")
    resetClass();
})
$(document).on("input", "input[type=radio]", function () {
    showOption = $(this).val();
    console.log(showOption)
})
function showingYou(classes, opt) {
    let a = classes.split(" ");
    let e = "";
    switch (opt) {
        case "0":

            $('td').removeClass("checking");
            $('td').removeClass("nowing");
            $(`.${a[3]}`).addClass("nowing");

            e = ".checking{background-color: rgba(239, 255, 189, 0.5)}"

            break;
        case "1":

            $('td').removeClass("nowing");
            $('td').removeClass("checking");
            $(`.${a[0]}`).addClass("checking");
            $(`.${a[3]}`).addClass("nowing");

            e = ".checking{background-color: rgb(252, 186, 211)}"
            break;
        case "2":

            $('td').removeClass("nowing");
            $('td').removeClass("checking");
            $(`.${a[1]}`).addClass("checking");
            $(`.${a[3]}`).addClass("nowing");

            e = ".checking{background-color: rgb(168, 223, 142)}"
            break;
        case "3":

            $('td').removeClass("nowing");
            $('td').removeClass("checking");
            $(`.${a[2]}`).addClass("checking");
            $(`.${a[3]}`).addClass("nowing");

            e = ".checking{background-color: rgb(168, 216, 234)}"
            break;
        case "4":

            $('td').removeClass("nowing");
            $('td').removeClass("checking");
            $(`.${a[1]}`).addClass("checking");
            $(`.${a[0]}`).addClass("checking");
            $(`.${a[3]}`).addClass("nowing");

            e = ".checking{background-color: rgb(170, 150, 218)}"
            break;
        default:

            $('td').removeClass("checking");
            $('td').removeClass("nowing");
            $(`.${a[3]}`).addClass("nowing");
            e = ".checking{background-color: rgba(42, 231, 122, 0.75)}"
            break;
    }
    if ($('body').children('style')) {
        $('body').children('style').remove();
    }
    $('body').prepend($(`<style>${e}</style>`));
}
$(document).on("click", "#showmeanswer", function () {
    let tmpArray = [];
    for (let i = 1; i < 82; i++) {

        if ($(`.T${i}`).children('input.showing').attr('name') == 0) {
            howmuchweMissed++;
            $(`.T${i}`).addClass("error")
        }
        else if ($(`.T${i}`).children('input.showing').attr('name') >= 1) {
            tmpArray.push(`.T${i}`);
        }

    }
    console.log(tmpArray)
    if (howmuchweMissed > 0) {
        alert("you missed " + howmuchweMissed + "blocks!")
        tmpArray = [];
        howmuchweMissed = 0;
    } else {
        console.log("all")
        determineThis(tmpArray);
        console.log("how" + howmuchweWrong)
        if (howmuchweWrong == 0) {

            let a = 0;
            for (let i = 1; i < 82; i++) {
                if ($(`.T${i}`).children('input').attr('name')>0) {
                    a++;
                }
            }
            if (a !== 0) {
                congraturationYou();
            } else {
                notcongraturationYou();
            }


        }
        else {
            console.log("some error")
        }
    }
})


$(document).on("click", '#wechecknum', function () {
    howmuchwedeletedareyou()
})


function howmuchwedeletedareyou() {
    // let a = 0;
    // for(let i=1; i<82; i++){
    //     if($(`.T${i}`).children('input').hasClass("showing")){
    //         a++;
    //     }
    // }
    // return a;

    console.log()
}
function resetClass() {
    $('td').removeClass("focusing");
    $('td').removeClass("checking");
    $('td').removeClass("nowing");
    $('td').removeClass("error");

}
$(document).on("input", ".error", function () {
    $(this).removeClass("error")
})

function determineThis(newarray) {
    console.log(newarray)
    // if (newarray.length==0){
    //     youdidntnoting = 1;
    //     console.log("123123")
    // }
    for (let i = 0; i < newarray.length; i++) {
        let a = newarray[i];
        let z = [];
        let b = $(a).attr("class");
        b = b.split(" ")
        let c = b[0];
        let d = b[1];
        let e = b[2];
        let f = b[3];
        for (let u = 0; u < 9; u++) {
            let c2 = $(`.${c}`).eq(u).children('input.showing').attr('name');
            let c3 = $(`.${c}`).eq(u).children('span.showing').text();
            let d2 = $(`.${d}`).eq(u).children('input.showing').attr('name');
            let d3 = $(`.${d}`).eq(u).children('span.showing').text();
            let e2 = $(`.${e}`).eq(u).children('input.showing').attr('name');
            let e3 = $(`.${e}`).eq(u).children('span.showing').text();
            z.push(c2);
            z.push(c3);
            z.push(d2);
            z.push(d3);
            z.push(e2);
            z.push(e3);
        }
        z = z.filter(el => el != null);
        z = z.filter(el => el != "");
        console.log(z)
        let f2 = $(`.${f}`).children('input.showing').attr('name');
        f2 += $(`.${f}`).children('span.showing').text();

        for (let u = 0; u < 3; u++) {

            let x = z.findIndex((num) => num == f2);

            z.splice(x, 1)
        }

        let q = z.findIndex((num) => num == f2);
        console.log(q)
        if (q != -1) {

            $(`.${f}`).addClass("error")
            howmuchweWrong++;
            console.log("wrong" + howmuchweWrong)
        }


    }
    if (!$('td').hasClass("error")) {
        howmuchweWrong = 0;
    }
}

function congraturationYou() {
    console.log("good")
    $('#messageHere').addClass("ononon")
}
$(document).on("click","#bottomL",function(){
    $('#messageHere').removeClass("ononon")
    doA();
})
$(document).on("click","#bottomR",function(){
    $('#messageHere').removeClass("ononon")
    
})

function notcongraturationYou() {
    
}

function addElement(a, b) {

    let c = 0;
    a = a.join("")
    c = parseInt(a)
    let d = 0;
    b = b.join("")
    d = parseInt(b)
    console.log("c" + c + " d" + d);
    c -= d;
    if (c < 0) {
        c = c * -1;
    }
    let e = c / 10000;
    if (e >= 1) {
        e = Math.floor(e);
        e = e * 10000;
        e = c - e;
    } else {
        e = e * 10000;
        e = Math.floor(e);
    }
    if (e <= 999) {
        e = e * 10
    }
    return e;

}

function giveAction(thiss) {
    thiss.animate({
        transform: 'scaleX(100%)',
        transform: 'scaleY(100%)'
    }, '0.4s')
    thiss.animate({
        transform: 'scaleX(110%)',
        transform: 'scaleY(90%)'
    }, '0.25s')
    thiss.animate({
        transform: 'scaleX(90%)',
        transform: 'scaleY(110%)'
    }, '0.2s')
    thiss.animate({
        transform: 'scaleX(100%)',
        transform: 'scaleY(100%)'
    }, '0.15s')

}

$(document).on('input','td input',function(){
	let temp = $(this).attr("name");
	if(temp !=null && temp !=""){
	$(this).removeClass('t-1')
	$(this).removeClass('t-2')
	$(this).removeClass('t-3')
	$(this).removeClass('t-4')
	$(this).removeClass('t-5')
	$(this).removeClass('t-6')
	$(this).removeClass('t-7')
	$(this).removeClass('t-8')
	$(this).removeClass('t-9')
	$(this).addClass(`t-${temp}`)}
})