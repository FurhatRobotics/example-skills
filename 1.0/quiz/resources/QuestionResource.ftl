<!DOCTYPE html>
<html class="no-js" lang="en" >

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <title>${resource.name}</title>
  <link rel="stylesheet" href="/css/foundation.css" />
  <link rel="stylesheet" href="/css/custom.css" />
  <link rel="stylesheet" href="/css/foundation-icons.css" />
  <script src="/js/vendor/modernizr.js"></script>
  <script src="/js/vendor/jquery.js"></script>
  <style>
  .row{
    max-width: none !important;
    margin-top: 10px !important;
    padding-bottom:10px !important;
    padding-left: 10px !important;
    padding-right: 10px !important;
  }
  .QuestionCard{
    padding-left: 10px !important;
    border-bottom: 1px dashed #b9b9b9;
    margin-right: 15px;
    width: 99%;
    transition: background .2s;
    margin-top: 0 !important;
    padding-top: 10px;
  }
  .QuestionCard:hover{
    background: #fcfcfc;
  }
  .QuestionCard:last-child{
    border: none !important;
  }
  .value, .key{
    display:block;
    padding-right:10px;
  }
  .key{
    border: none !important;
    box-shadow: none !important;
    border-bottom: 1px solid black !important;
    padding-bottom: 0px !important;
    padding-left: 0px !important;
    margin-bottom: 0 !important;
  }
  .value{
    width:80%;
    margin-bottom: 0px !important;
    margin-top:10px !important;
    display: inline;
  }
  .addBtn{
    margin-bottom: 0px !important;
    margin-top: 20px !important;
    font-style: normal;
    font-size: small;
    color: #76bed6;
    text-transform: lowercase;
    padding-top: 0px !important;
    display: inline-block;
    border-bottom: 1px dotted #76bed6;
  }
  .newBtn{
    position: fixed;
    z-index: 99;
    margin-left: 25px;
    bottom: 0;
    left: 70px;
/*hello Subbu*/
  }
  #formElements{
    margin-top: 50px;
    margin-bottom: 50px;
  }
  .btnBar{
    margin-top: 10px;
    position: fixed;
    bottom: -20px;
    left: 35px;
    background: white;
    width: 100vw;
    padding-top: 10px;
    z-index: 2;
    margin-left: -34px;
    padding-left: 20px;
    border-top: 1px dotted #9a9a9a;
  }
  .trashCan {
    width: 20px;
    height: 20px;
    padding: 0.8em;
    cursor: pointer;
    display: inline-block;
    margin-bottom: 13px;
    margin-left: 4px;
    max-width: 256px;
    max-height: 256px;
    border-radius: 14%;
    background: #e7e7e7;
    background-repeat: no-repeat;
    background-size: 15px;
    background-position: 5px;
    background-image: url(data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pgo8IS0tIEdlbmVyYXRvcjogQWRvYmUgSWxsdXN0cmF0b3IgMTkuMS4wLCBTVkcgRXhwb3J0IFBsdWctSW4gLiBTVkcgVmVyc2lvbjogNi4wMCBCdWlsZCAwKSAgLS0+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayIgdmVyc2lvbj0iMS4xIiBpZD0iQ2FwYV8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDQ4Ni40IDQ4Ni40IiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA0ODYuNCA0ODYuNDsiIHhtbDpzcGFjZT0icHJlc2VydmUiIHdpZHRoPSIyNTZweCIgaGVpZ2h0PSIyNTZweCI+CjxnPgoJPGc+CgkJPHBhdGggZD0iTTQ0Niw3MEgzNDQuOFY1My41YzAtMjkuNS0yNC01My41LTUzLjUtNTMuNWgtOTYuMmMtMjkuNSwwLTUzLjUsMjQtNTMuNSw1My41VjcwSDQwLjRjLTcuNSwwLTEzLjUsNi0xMy41LDEzLjUgICAgUzMyLjksOTcsNDAuNCw5N2gyNC40djMxNy4yYzAsMzkuOCwzMi40LDcyLjIsNzIuMiw3Mi4yaDIxMi40YzM5LjgsMCw3Mi4yLTMyLjQsNzIuMi03Mi4yVjk3SDQ0NmM3LjUsMCwxMy41LTYsMTMuNS0xMy41ICAgIFM0NTMuNSw3MCw0NDYsNzB6IE0xNjguNiw1My41YzAtMTQuNiwxMS45LTI2LjUsMjYuNS0yNi41aDk2LjJjMTQuNiwwLDI2LjUsMTEuOSwyNi41LDI2LjVWNzBIMTY4LjZWNTMuNXogTTM5NC42LDQxNC4yICAgIGMwLDI0LjktMjAuMyw0NS4yLTQ1LjIsNDUuMkgxMzdjLTI0LjksMC00NS4yLTIwLjMtNDUuMi00NS4yVjk3aDMwMi45djMxNy4ySDM5NC42eiIgZmlsbD0iIzhhOGE4YSIvPgoJCTxwYXRoIGQ9Ik0yNDMuMiw0MTFjNy41LDAsMTMuNS02LDEzLjUtMTMuNVYxNTguOWMwLTcuNS02LTEzLjUtMTMuNS0xMy41cy0xMy41LDYtMTMuNSwxMy41djIzOC41ICAgIEMyMjkuNyw0MDQuOSwyMzUuNyw0MTEsMjQzLjIsNDExeiIgZmlsbD0iIzhhOGE4YSIvPgoJCTxwYXRoIGQ9Ik0xNTUuMSwzOTYuMWM3LjUsMCwxMy41LTYsMTMuNS0xMy41VjE3My43YzAtNy41LTYtMTMuNS0xMy41LTEzLjVzLTEzLjUsNi0xMy41LDEzLjV2MjA4LjkgICAgQzE0MS42LDM5MC4xLDE0Ny43LDM5Ni4xLDE1NS4xLDM5Ni4xeiIgZmlsbD0iIzhhOGE4YSIvPgoJCTxwYXRoIGQ9Ik0zMzEuMywzOTYuMWM3LjUsMCwxMy41LTYsMTMuNS0xMy41VjE3My43YzAtNy41LTYtMTMuNS0xMy41LTEzLjVzLTEzLjUsNi0xMy41LDEzLjV2MjA4LjkgICAgQzMxNy44LDM5MC4xLDMyMy44LDM5Ni4xLDMzMS4zLDM5Ni4xeiIgZmlsbD0iIzhhOGE4YSIvPgoJPC9nPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+Cjwvc3ZnPgo=)
  }
  .deleteBtn{
    position: absolute;
    width: 50px;
    right: 0;
    top: 0;
    height: 50px;
    background-size: 20px;
    background-repeat: no-repeat;
    background-position: 14px;
    vertical-align: middle;
    background-image: url(data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pgo8IS0tIEdlbmVyYXRvcjogQWRvYmUgSWxsdXN0cmF0b3IgMTkuMS4wLCBTVkcgRXhwb3J0IFBsdWctSW4gLiBTVkcgVmVyc2lvbjogNi4wMCBCdWlsZCAwKSAgLS0+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayIgdmVyc2lvbj0iMS4xIiBpZD0iQ2FwYV8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDQ4Ni40IDQ4Ni40IiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA0ODYuNCA0ODYuNDsiIHhtbDpzcGFjZT0icHJlc2VydmUiIHdpZHRoPSIyNTZweCIgaGVpZ2h0PSIyNTZweCI+CjxnPgoJPGc+CgkJPHBhdGggZD0iTTQ0Niw3MEgzNDQuOFY1My41YzAtMjkuNS0yNC01My41LTUzLjUtNTMuNWgtOTYuMmMtMjkuNSwwLTUzLjUsMjQtNTMuNSw1My41VjcwSDQwLjRjLTcuNSwwLTEzLjUsNi0xMy41LDEzLjUgICAgUzMyLjksOTcsNDAuNCw5N2gyNC40djMxNy4yYzAsMzkuOCwzMi40LDcyLjIsNzIuMiw3Mi4yaDIxMi40YzM5LjgsMCw3Mi4yLTMyLjQsNzIuMi03Mi4yVjk3SDQ0NmM3LjUsMCwxMy41LTYsMTMuNS0xMy41ICAgIFM0NTMuNSw3MCw0NDYsNzB6IE0xNjguNiw1My41YzAtMTQuNiwxMS45LTI2LjUsMjYuNS0yNi41aDk2LjJjMTQuNiwwLDI2LjUsMTEuOSwyNi41LDI2LjVWNzBIMTY4LjZWNTMuNXogTTM5NC42LDQxNC4yICAgIGMwLDI0LjktMjAuMyw0NS4yLTQ1LjIsNDUuMkgxMzdjLTI0LjksMC00NS4yLTIwLjMtNDUuMi00NS4yVjk3aDMwMi45djMxNy4ySDM5NC42eiIgZmlsbD0iIzhhOGE4YSIvPgoJCTxwYXRoIGQ9Ik0yNDMuMiw0MTFjNy41LDAsMTMuNS02LDEzLjUtMTMuNVYxNTguOWMwLTcuNS02LTEzLjUtMTMuNS0xMy41cy0xMy41LDYtMTMuNSwxMy41djIzOC41ICAgIEMyMjkuNyw0MDQuOSwyMzUuNyw0MTEsMjQzLjIsNDExeiIgZmlsbD0iIzhhOGE4YSIvPgoJCTxwYXRoIGQ9Ik0xNTUuMSwzOTYuMWM3LjUsMCwxMy41LTYsMTMuNS0xMy41VjE3My43YzAtNy41LTYtMTMuNS0xMy41LTEzLjVzLTEzLjUsNi0xMy41LDEzLjV2MjA4LjkgICAgQzE0MS42LDM5MC4xLDE0Ny43LDM5Ni4xLDE1NS4xLDM5Ni4xeiIgZmlsbD0iIzhhOGE4YSIvPgoJCTxwYXRoIGQ9Ik0zMzEuMywzOTYuMWM3LjUsMCwxMy41LTYsMTMuNS0xMy41VjE3My43YzAtNy41LTYtMTMuNS0xMy41LTEzLjVzLTEzLjUsNi0xMy41LDEzLjV2MjA4LjkgICAgQzMxNy44LDM5MC4xLDMyMy44LDM5Ni4xLDMzMS4zLDM5Ni4xeiIgZmlsbD0iIzhhOGE4YSIvPgoJPC9nPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+Cjwvc3ZnPgo=);
    cursor: pointer;
    transition: background-color .2s;
  }
  .deleteBtn:hover{
    background-color: #e8e8e8;
  }
  .deleteWrap{
    margin: 0 !important;
    padding: 0 !important;
  }
  .desc{
    box-shadow: none;
    border: none;
    font-style: italic;
    background: #f5f5f5;
  }
  </style>
</head>
<body style="padding-left:10px">
  <textarea id="fileText" style="display:none">${resource.text?html}</textarea>
  <form id="formElements"></form>

  <p class="btnBar" style="margin-top:10px">
    <a href="javascript:save()" class="button tiny radius">Save</a>
    <span id="message" style="font-size:10pt;  padding-left:393px"></span>
  </p>

  <script src="/js/ace/ace.js" type="text/javascript" charset="utf-8"></script>
  <script>
  var description;
  function loadForm(){
    $(document).bind('keydown', function(event) {
      if (event.ctrlKey || event.metaKey) {
        switch (String.fromCharCode(event.which).toLowerCase()) {
          case 's':{
            event.preventDefault();
            save();
            break;
          }
          case 'l':{
            event.preventDefault();
            if (event.currentTarget.activeElement.value.length > 0) {
              say(event.currentTarget.activeElement.value);
            }
            break;
          }
        }
      }
    });


    var fileText = $("#fileText").val();
    var lines = fileText.split("\n");
    var commentValue, values = 0;
    description = lines[0].split(';');
    for(var i=1;i<lines.length && lines[i]!="";i++){ // Skipping the first line
      console.log("Data Line");
      values  = lines[i].split(';');
      //Start of HTML form.....
      $("#formElements").append('<div class="QuestionCard row" id="line'+i+'"></div>');
      $("#line"+i+"").append('<div class="small-3 large-3 columns" id="'+i+'"></div><div class="small-8 large-8 columns" id="line'+i+'_value"></div><div class="deleteWrap small-1 large-1 columns" id="line'+i+'_delete"><div class="deleteBtn"></div></div>')
      //Exactly 7 data fields per line.
      $("#line"+i+"_value").append('<div class="row collapse prefix-radius" style="text-align:center"> <div class="small-2 columns"><span class="prefix">'+description[0]+' '+i+'.</span></div> <div class="small-10 columns"> <input type="text" value="'+values[0]+'" id="input_'+i+'_'+0+'"/> </div> </div> ');//Question
      //  $("#line"+i+"_value").append('<form> <div class="row"> <div class="small-3 columns"><label for="middle-label" class="text-right middle">'+description[0]+'</labbel></div> <div class="small-9 columns"> <input type="text" id="middle-label" value="'+values[0]+' "/> </div></div></form> ');

      //Category   | Difficulty lables
      $("#line"+i+"_value").append('<div class="row" id="unevenForm'+i+'"></div>');
      //All next 4 lines on 12 grid line. should not be divs....
      $("#unevenForm"+i).append('<div class="small-9 columns"><div class="row collapse prefix-radius" id="unevenFormLeft'+i+'" ></div></div> ');
      $("#unevenFormLeft"+i).append('<div class="small-3 columns"> <span class="prefix">' +description[5]+'</span></div>'); // category label
      $("#unevenFormLeft"+i).append('<div class="small-9 columns"> <input id="input_'+i+'_'+5+'" type="text" value="'+values[5]+' "/></div>');// Cat input small-5 columns

      $("#unevenForm"+i).append('<div class="small-3 columns"><div class="row collapse prefix-radius" id="unevenFormRight'+i+'"><div></div> ');
      $("#unevenFormRight"+i).append('<div class="small-6 columns"> <span class="prefix">' +description[6]+'</span></div>'); // Difficulty small-2 columns
      $("#unevenFormRight"+i).append('<div class="small-6 columns"><input id="input_'+i+'_'+6+'" type="number" name="quantity" min="1" max="10" value="'+values[6]+'"/></div>'); // Diff input small-2 columns
      //                                            14
      //Inputs
      //1-2 ,3-4
      for(var j=1; j<4;j+=2){
        //Alternative answers and category. 2rowsx2cols
        $("#line"+i+"_value").append('<div class="row" id="choice'+i+''+j+'"></div>'); // main container for 2 forms (span-input)
        //choice left
        $("#choice"+i+""+j).append('<div class="small-6 columns"><div class="row collapse prefix-radius" id="choiceLeft'+i+''+j+'" ></div></div>');
        $("#choiceLeft"+i+""+j).append('<div class="small-3 columns"> <span class="prefix">' +description[j]+'</span></div>'); //add style?
        $("#choiceLeft"+i+""+j).append('<div class="small-9 columns"><input id="input_'+i+'_'+j+'" type="text" value="'+values[j]+' "/></div> ');

        //choice right
        $("#choice"+i+""+j).append('<div class="small-6 columns"><div class="row collapse prefix-radius" id="choiceRight'+i+''+j+'" ></div></div>');
        $("#choiceRight"+i+""+j).append('<div class="small-3 columns"> <span class="prefix">' +description[j+1]+'</span></div>'); //add style?
        $("#choiceRight"+i+""+j).append('<div class="small-9 columns"><input id="input_'+i+'_'+(j+1)+'" type="text" value="'+values[j+1]+' "/></div> ');
      }

      //Difficulty
    }//end of for(i<lines.length)
    $("#formElements").before('<a id="'+lines.length+'" class="newBtn button tiny radius">New Value</a>');

    $(".newBtn").click(function(){
      //TODO change this to add a new questioncard as mentioned above.
      var i = parseInt($('.QuestionCard.row')[$('.QuestionCard.row').length-1].children[0].id) +1;
      $("#formElements").append('<div class="QuestionCard row" id="line'+i+'"></div>');
      $("#line"+i+"").append('<div class="small-3 large-3 columns" id="'+i+'"></div><div class="small-8 large-8 columns" id="line'+i+'_value"></div><div class="deleteWrap small-1 large-1 columns" id="line'+i+'_delete"><div class="deleteBtn"></div></div>')
      //Exactly 7 data fields per line.
      $("#line"+i+"_value").append('<div class="row collapse prefix-radius" style="text-align:center"> <div class="small-2 columns"><span class="prefix">'+description[0]+' '+i+'.</span></div> <div class="small-10 columns"> <input id="input_'+i+'_'+0+'" type="text"/> </div> </div> ');//Question
      //  $("#line"+i+"_value").append('<form> <div class="row"> <div class="small-3 columns"><label for="middle-label" class="text-right middle">'+description[0]+'</labbel></div> <div class="small-9 columns"> <input type="text" id="middle-label" value="'+values[0]+' "/> </div></div></form> ');

      //Category   | Difficulty lables
      $("#line"+i+"_value").append('<div class="row" id="unevenForm'+i+'"></div>');
      //All next 4 lines on 12 grid line. should not be divs....
      $("#unevenForm"+i).append('<div class="small-9 columns"><div class="row collapse prefix-radius" id="unevenFormLeft'+i+'" ></div></div> ');
      $("#unevenFormLeft"+i).append('<div class="small-3 columns"> <span class="prefix">' +description[5]+'</span></div>'); // category label
      $("#unevenFormLeft"+i).append('<div class="small-9 columns"> <input id="input_'+i+'_'+5+'" type="text"/></div>');// Cat input small-5 columns

      $("#unevenForm"+i).append('<div class="small-3 columns"><div class="row collapse prefix-radius" id="unevenFormRight'+i+'"><div></div> ');
      $("#unevenFormRight"+i).append('<div class="small-6 columns"> <span class="prefix">' +description[6]+'</span></div>'); // Difficulty small-2 columns
      $("#unevenFormRight"+i).append('<div class="small-6 columns"><input id="input_'+i+'_'+6+'" type="number" name="quantity" min="1" max="10" value="1"/></div>'); // Diff input small-2 columns
      //                                            14
      //Inputs
      //1-2 ,3-4
      for(var j=1; j<4;j+=2){
        //Alternative answers and category. 2rowsx2cols
        $("#line"+i+"_value").append('<div class="row" id="choice'+i+''+j+'"></div>'); // main container for 2 forms (span-input)
        //choice left
        $("#choice"+i+""+j).append('<div class="small-6 columns"><div class="row collapse prefix-radius" id="choiceLeft'+i+''+j+'" ></div></div>');
        $("#choiceLeft"+i+""+j).append('<div class="small-3 columns"> <span class="prefix">' +description[j]+'</span></div>'); //add style?
        $("#choiceLeft"+i+""+j).append('<div class="small-9 columns"><input id="input_'+i+'_'+j+'" type="text"/></div> ');

        //choice right
        $("#choice"+i+""+j).append('<div class="small-6 columns"><div class="row collapse prefix-radius" id="choiceRight'+i+''+j+'" ></div></div>');
        $("#choiceRight"+i+""+j).append('<div class="small-3 columns"> <span class="prefix">' +description[j+1]+'</span></div>'); //add style?
        $("#choiceRight"+i+""+j).append('<div class="small-9 columns"><input id="input_'+i+'_'+(j+1)+'" type="text"/></div> ');
      }
      //$(".newBtn")[0].id = parseInt(currLine)+1;
      $('#input_'+i+'_'+0).focus();
      $("html, body").animate({ scrollTop: $(document).height() }, 100);

    });

    $("body").on('click','div.trashCan', function(event){
      $(this)[0].parentNode.remove();
    });

    $("body").on('click','div.deleteBtn', function(event){
      $(this)[0].parentNode.parentNode.remove()
    });
  }

  function say(text) {
    $.get("/head/face/event?event=action.speech&text=" + text);
  }

  function readForm(){
    var data = description[0];
  //  $("#formElements").children()[X].id.substring(4)
    for (var i=1; i< description.length; i++){
      data+=";"+description[i];
    }
    data+='\n';
    for (var i=0; i < $("#formElements").children().length ;i++) {//right size
      var index = parseInt($("#formElements").children()[i].id.substring(4));
      for (var j=0; j < description.length ;j++) {
          data+=$.trim(document.getElementById("input_"+index+"_"+j).value);
          data+=';';
      }// for each field
        data+='\n';
    }// for each Question Card
    return data;
  }

  function save() {
    $("#form").attr("action", "save");
    document.getElementById("text").value = readForm();
    document.getElementById("form").submit();
  }

  function hiddenLoaded() {
    var data = new Object();
    try {
      data = JSON.parse($("#hidden").contents().text());
    } catch (err) {
    }
    if ("status" in data) {
      if (data.status == "error") {
        $("#message").text("Error line " + data.line + ": " + data.message);
        $("#message").css("color", "red");
      } else {
        $("#message").text(data.status);
        $("#message").css("color", "green");
        if(data.status == "Uploaded"){
        	alert("Uploaded Successfully")
        	location.reload();
        }
      }
    }
  }
  
  function submitFile(){
	if($("#file")[0].files[0].size > 0) {  
	  	if($("#file").val().split('.')[$("#file").val().split('.').length-1] == "txt"){
		  	document.getElementById('upload_form').submit();
	  	}
	  	else{
	  		alert("File format not supported. Please upload text(.txt) file only.");
	  	}
	}
	else {
		alert("Empty File cannot be uploaded. Put in 'Question;Correct;Answer2;Answer3;Answer4;Category;Difficulty' as the first line of the text file");
	}
  }
  </script>

  <form id="form" action="textedit" method="post" style="display:none" target="hidden">
    <textarea id="text" name="text"></textarea>
  </form>
  
  <form action="export" style="position: fixed;
    bottom: 0;
    left: 196px;
    z-index: 100;">
  	<input type="submit" value="Download Questions" class="button tiny radius">
  </form>
  
  <form action="import" method="post" enctype="multipart/form-data" id="upload_form" style="display:none" target="hidden">
    <input type="file" name="file" id="file" onchange="submitFile()">
  </form>
  <a href="javascript:document.getElementById('file').click()" class="button tiny radius" style="position: fixed;
    z-index: 1000;
    bottom: 0;
    left: 348px;">Upload Questions</a>
  

  <iframe name="hidden" id="hidden" style="display:none" onload="hiddenLoaded()"></iframe>

  <script>
  window.addEventListener('load', loadForm, true);
  </script>

</body>
</html>
