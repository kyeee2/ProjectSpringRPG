var doc = document;  
  var form1 = doc.getElementById('form1');  
  var inputs = form1.getElementsByTagName('INPUT');  
  var form1_data = { 
   "c1": false,  //전체 선택
   "c2": false,  //필수 선택 1
   "c3": false   //필수 선택 2
  };  
  
  var c1 = doc.getElementById('c1');  
  var c2 = doc.getElementById('c2');  
  var c3 = doc.getElementById('c3');  
  
  function checkboxListener() { 
   form1_data[this.name] = this.checked; //각각 자신의 checkBox를 Checked 상태로 바꿈  
  } 
  
  
   c1.onclick = c2.onclick = c3.onclick = checkboxListener;   //c1, C2, C3를 checkBoxListenner를 호출하여
                                                                   //Checked로 바꿈
   var all = doc.getElementById('all');  //전체 체크를 위한 체크박스 선언
  
   all.onclick = function() {  //전체 체크를 누를 시
    if (this.checked) { 
     setCheckbox(form1_data, true);   //form1_data(c1,c2,c3)의 값을 모두 Checked로 바꿈
    } else { 
     setCheckbox(form1_data, false);  ////form1_data(c1,c2,c3)의 값을 모두 no checked로 바꿈
    } 
   };  
  
  
   function setCheckbox(obj, state) { //checkbox상태 변경하는 함수
    for (var x in obj) { 
     obj[x] = state;  
  
     for(var i = 0; i < inputs.length; i++) { 
      if(inputs[i].type == "checkbox") { 
       inputs[i].checked = state;  
      } 
     } 
  
    } 
   } 
  
  form1.onsubmit = function(e) { 
   e.preventDefault(); 
  
   if ( !form1_data['c1'] ) { 
    alert('이용동의 약관에 동의하지 않았습니다.');   
    return false;  
   } 
  
   if ( !form1_data['c2'] ) { 
    alert('개인정보 수집 및 이용에 대한 안내를 동의하지 않았습니다.');  
    return false;  
   } 
  
   this.submit();  
  };  
  $(function() {
    $( "#accordion" ).accordion();
    $('#accordion input[type="checkbox"]').click(function(e) {
        e.stopPropagation();
    });
  });