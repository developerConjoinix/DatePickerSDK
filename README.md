<p><strong>Easy to use Date Time picker and enormous date converter.</strong></p>
<pre>ConjoinxDate.Builder()<br/>.title("Choose date of birth") <span style="color: #fff;"> /*optional*/</span>
.maxDate(maxDate) <span style="color: #999999;">// optional </span>
.setdateTime(myDate) <span style="color: #999999;">// optional</span>
.minDate(miniDate) <span style="color: #999999;">// optional</span>
.pickerType(ConjoinxDate.Picker.DATE) <span style="color: #999999;">//optional (Default is Picker.DATETIME )</span>  
.build(this){ time -&gt; <span style="color: #999999;">// In String</span><br />        <br />        <br />    }</pre>

<p><strong>Enormous date converter.</strong></p>

<pre><span style="color: #fff;"> var a = "2019-01-07 14:11:00"
a.timeAgo()  = 2 days ago
a.toDate().time.convertToReadable = 11 hours : 23 mint

and many more....
</span>
</pre>


<pre><p><strong>Add following lib to your app level Gradle</strong></p>
<p>dependencies {<br/>implementation 'com.github.developerConjoinix:DatePickerSDK:1.3'<br />}<strong><br /></strong></p></pre>
<p>&nbsp;</p>
<pre><p><strong>Add following lib to your project level Gradle</strong></p>
<div>allprojects {</div>
<div>repositories {</div>
<div>...</div>
<div> maven { url 'https://jitpack.io' } </div>
<div>}</div>
<div>}</div>
</pre>
<br/> <br/> <br/>


![Optional Text](../master/Screenshot/ad.png)
![Optional Text](../master/Screenshot/ad1.png)
![Optional Text](../master/Screenshot/ad2.png)

 

