import { BASE_URL } from "./url.js";
import * as rd from "./rendering.js";
import * as fc from "./functions.js";
import * as ld from "./load.js";

const l_url = "/res/mob.json"

ld.jsonData((data) => {
    let mobjson = data;
    const g_bc_level = rd.g_bc_level;
    const mobFilterasLv = mobjson.filter((mob) => mob.mob_level === g_bc_level);

    const mobMapasName = mobFilterasLv.map((mob) => mob.mob_name);
    const mobNameList = [...new Set(mobMapasName)];

    setMonster(
        mobNameList,
        rd.g_bc_w,
        rd.g_bc_h,
        rd.g_bc_monsters,
        mobFilterasLv,
        rd.g_bc_monsterSize,
        rd.g_bc,
        () => {
            // Callback function to start animation after each mob is created
            moveMobToCenter();
        }
    );

    function moveMobToCenter() {
        let targetX = rd.g_bc_x,
            targetY = rd.g_bc_y,
            mobSpeed = 20;

        $('.mob').each(function () {
            let singleMob = $(this);
            let singleMob_x = parseFloat(singleMob.css('left'));
            let singleMob_y = parseFloat(singleMob.css('top'));
            let initialX = singleMob_x;
            // Calculate the distance to the target
            let deltaX = targetX - singleMob_x;
            let deltaY = targetY - singleMob_y;

            // Calculate the angle to the target
            let angle = Math.atan2(deltaY, deltaX);

            // Define the move function
            function move() {
                // Calculate the new position based on the angle and speed
                singleMob_x += Math.cos(angle) * mobSpeed;
                singleMob_y += Math.sin(angle) * mobSpeed;

                // Update the element's position
                singleMob.css({
                    left: singleMob_x + 'px',
                    top: singleMob_y + 'px',
                });

                // Check if the element has reached the target
                if (
                    Math.abs(singleMob_x - targetX) < mobSpeed &&
                    Math.abs(singleMob_y - targetY) < mobSpeed
                ) {
                    singleMob.css({
                        backgroundImage : `${singleMob.data('mob-def')}`,
                        aspectRatio : `${singleMob.data('mob-def-x')}/${singleMob.data('mob-def-y')}`,
                        transform : `scaleX(1)`
                    })
                    clearInterval(animationInterval); // Stop the animation when the target is reached
                }
                if(singleMob_x > initialX){ //to left
                    singleMob.css({
                        backgroundImage : `${singleMob.data('mob-mov')}`,
                        aspectRatio : `${singleMob.data('mob-mov-x')}/${singleMob.data('mob-mov-y')}`,
                        transform : `scaleX(-1)`
                    })
                }else{//to right
                    singleMob.css({
                        backgroundImage : `${singleMob.data('mob-mov')}`,
                        aspectRatio : `${singleMob.data('mob-mov-x')}/${singleMob.data('mob-mov-y')}`,
                        transform : `scaleX(1)`
                    })
                }
                
            }

            // Set up the animation loop
            let animationInterval = setInterval(move, 50); // Adjust the interval as needed
        });
    }
}, l_url);

function setMonster(
    moblist,
    mapWidth,
    mapHeight,
    mobCounter,
    mobFullList,
    mobSize,
    original,
    callback
) {
    for (let i = 0; i < mobCounter; i++) {
        setTimeout(function () {
            let tempN0 = fc.functions_RandomStartEndSize(
                0,
                moblist.length - 1,
                moblist.length * 10
            );
            let singleMobName = moblist[tempN0];
            let singleMobObj = mobFullList.filter(
                (mob) => mob.mob_name === singleMobName
            );
            let singleMobDef = singleMobName + '00';
            singleMobDef = singleMobObj.filter(
                (mob) => mob.mob_tag == singleMobDef
            )[0];
            let singleMobMov = singleMobName + '10';
            singleMobMov = singleMobObj.filter(
                (mob) => mob.mob_tag == singleMobMov
            )[0];
            let singleMobDie = singleMobName + '30';
            singleMobDie = singleMobObj.filter(
                (mob) => mob.mob_tag == singleMobDie
            )[0];
            let tempS = singleMobDef.mob_gif > 0 ? '.gif' : '.png';
            let tempS1 = singleMobDie.mob_gif > 0 ? '.gif' : '.png';
            let tempS2 = singleMobMov.mob_gif > 0 ? '.gif' : '.png';
            let tempN1 = fc.functions_RandomStartEndSize(
                0,
                mapWidth - singleMobDef.mob_x,
                mapWidth * 10
            );
            let tempN2 = fc.functions_RandomStartEndSize(
                0,
                mapHeight - singleMobDef.mob_y,
                mapHeight * 10
            );

            let tempString = `<div class="mob mob_${i}" 
            data-mob-name = "${singleMobName}";
            data-mob-def = "url('./css/mob/${singleMobDef.mob_tag}${tempS}')";
            data-mob-def-x = "${singleMobDef.mob_x}";
            data-mob-def-y = "${singleMobDef.mob_y}";
            data-mob-die = "url('./css/mob/${singleMobDie.mob_tag}${tempS1}')";
            data-mob-die-x = "${singleMobDie.mob_x}";
            data-mob-die-y = "${singleMobDie.mob_y}";
            data-mob-mov = "url('./css/mob/${singleMobMov.mob_tag}${tempS2}')";
            data-mob-mov-x = "${singleMobMov.mob_x}";
            data-mob-mov-y = "${singleMobMov.mob_y}";
            style="
                aspect-ratio : ${singleMobDef.mob_x} / ${singleMobDef.mob_y};
                height: ${mobSize}px;
                background-image : url('./css/mob/${singleMobDef.mob_tag}${tempS}');
                background-size:100% 100%;
                left:${tempN1}px;
                top:${tempN2}px;
                position:absolute;
            "
        ></div>`;
            $(original).prepend(tempString);

            if (callback) {
                callback(); // Invoke the callback function after creating each mob element
            }
        }, 100);
    }
}
