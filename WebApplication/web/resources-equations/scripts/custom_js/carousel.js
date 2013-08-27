var index = 2;
var maxIndex;
var slideTime = 8000;
var fadeInTime = 1000;
var t;

$(function() {
    $(".foo ul li").each(function (index, domEle) {
        $(domEle).addClass("c_" + (index+1));
        maxIndex = index + 1;
    });

    $(".foo ul li").hide();
    $(".foo ul li:first").fadeIn(fadeInTime);
    $(".foo .prev").click(function () {
        clearInterval(t);
        showElement(index - 2);
        t = setInterval("autoHide()", slideTime);
    });

    $(".foo .next").click(function () {
        clearInterval(t);
        showElement(index);
        t = setInterval("autoHide()", slideTime);
    });

    t = setInterval("autoHide()", slideTime);
});

function showElement(pos) {
    index = pos;
    if (index <= 0) {
        index = maxIndex;
    }

    if (index > maxIndex) {
        index = 1;
    }
    $(".foo ul li").hide();
    $(".foo .c_" + index).fadeIn(fadeInTime);
    index = index + 1;
}

function autoHide() {
    showElement(index);
}