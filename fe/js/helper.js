
var todoList = {
    todos: [
/*        {
            id: 1,
            todo: 'one',
            completed: 0,
            date: 1496484012450
        }*/
    ]
};      //todoList를 디비에서 받아서 처음 init하자     
//var completeList = ['one', 'two', 'three'];  //completeList를 디비에서 받아서 처음 init하자    둘다 label의 text값만 가지고 있게 
//가능하면 최신이 가장 앞에 들어가도록 


var li_add_form = '<li><div class="' + 'view"><input class="'+'toggle" type="'+'checkbox"><label></label><button class='+'"destroy'+'"></button></div><input class="'+'edit" value='+'"Rule the web'+'"></li>';
var li_compelet_form = '<li class="'+'completed"' + '><div class="' + 'view"><input class="'+'toggle" type="'+'checkbox" checked="'+'checkbox"><label></label><button class='+'"destroy'+'"></button></div><input class="'+'edit" value='+'"Create a TodoMVC template'+'"></li>';
var listState = 0;