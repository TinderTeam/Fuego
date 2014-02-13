
 function validateEmpty(btnID,disValue)
 {
      var value=document.getElementById(btnID).value;
     if(value=='')
     {
       alert(disValue); 
      document.getElementById(btnID).focus();
      return false;
   }
   //其他同上验证！
   return true;

 } 
