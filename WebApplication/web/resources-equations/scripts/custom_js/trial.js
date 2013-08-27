// JavaScript Document

function newFunc(){
    $("#main_menu_left").animate({
        opacity: '0',
        opacity: '1'
    }, 1000);
    $("#main_menu_right").animate({
        opacity: '0',
        opacity: '1'
    }, 1000);
    alert($("#main_menu_left").get(0));
    $("#main_content").animate({
        width:'0',
        width:'100%'
    }, 500);
}