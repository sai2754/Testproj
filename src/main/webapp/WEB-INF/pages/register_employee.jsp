<%@page isELIgnored="false" %>
<%@taglib  uri="http://www.springframework.org/tags/form"  prefix="frm"%>
<h1 style="color:red; text-align:center;">Register Employee</h1>
<frm:form  modelAttribute="emp">
    <table  bgcolor="cyan" align="center">
    <tr>
       <td>Employee Name::</td>
       <td><frm:input path="ename"/></td>
    </tr>
    <tr>
       <td>Employee Desg::</td>
       <td><frm:input path="job"/> </td>
    </tr>
    <tr>
       <td>Employee salary::</td>
       <td><frm:input path="sal"/></td>
    </tr>
    <tr>
       <td>Dept no::</td>
       <td><frm:input path="deptno"/></td>
    </tr>
    <tr>
     <td><input type="submit"  value="Register"></td>
      <td><input type="reset"  value="cancel"></td>
    </tr>
    </table>
  </frm:form>
