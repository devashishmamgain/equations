$(document).ready(function(){
    create_border();
});

function create_border(){
    $('h3').corner("6px");
    $('h2').corner("top 6px");
    $('input, textarea').css({
        "border-width": "1px",
        "border-style": "solid",
        "border-color": "#9c9c9c"
    });
    $('input').attr('size', 35);
    $('textarea').attr({
        'cols': '27'
    });

   
    if(!$.browser.msie){
        $('input, textarea').corner("4px");
    }
    
    $('h1').corner("top 6px");
    $('#main_menu_left').corner("bottom 5px");
    $('#main_content').children('.foo').corner("6px");
    $('#main_content').children("#left_content").children(".left_content").corner("6px");

//$('#main_content').children().children().corner("4px");
//$('#main_content').children().children().children().corner("4px");
}	