const canvasNext = document.createElement("script");
canvasNext.color = "0, 0, 0"; // 粒子颜色(RGB)
canvasNext.opacity = '0.9';   // 粒子透明度
canvasNext.zIndex = '-1';     // 画布层级
canvasNext.count = '20';     // 粒子数量
canvasNext.src = 'https://cdn.bootcss.com/canvas-nest.js/1.0.1/canvas-nest.min.js';
var windowWidth = $(window).width();
if(windowWidth < 768){
    // do something
}
if(windowWidth >= 768){
    document.body.appendChild(canvasNext)
}
