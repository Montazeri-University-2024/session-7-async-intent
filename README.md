### استفاده ساده از Coroutine در Jetpack Compose

Coroutine‌ها در کاتلین کمک می‌کنند کارهای طولانی مثل دریافت داده را بدون متوقف کردن رابط کاربری انجام دهیم. در Jetpack Compose، با این ابزارها آشنا شوید:

---

#### ابزارهای اصلی

##### ۱. **`coroutineScope`**  
این تابع به شما اجازه می‌دهد چند کار (Coroutine) را همزمان اجرا کنید. اگر یکی از کارها شکست بخورد، بقیه هم لغو می‌شوند.  
**مثال:**  
```kotlin
suspend fun loadData() = coroutineScope {
    launch { fetchDataPart1() }
    launch { fetchDataPart2() }
}
```

---

##### ۲. **`launch`**  
برای اجرای کارهای پس‌زمینه‌ای مثل دریافت داده استفاده می‌شود.  
**مثال:**  
```kotlin
coroutineScope.launch {
    val data = fetchData()
    updateUI(data)
}
```

---

##### ۳. **`rememberCoroutineScope`**  
این تابع یک `CoroutineScope` ایجاد می‌کند که با چرخه حیات composable هماهنگ است. اگر composable حذف شود، Coroutine‌ها لغو می‌شوند.  
**مثال:**  
```kotlin
@Composable
fun MyComposable() {
    val coroutineScope = rememberCoroutineScope()

    Button(onClick = {
        coroutineScope.launch {
            performAction()
        }
    }) {
        Text("Start Action")
    }
}
```

---

### مثال کاربردی

```kotlin
@Composable
fun DataScreen() {
    val coroutineScope = rememberCoroutineScope()
    var data by remember { mutableStateOf<String?>(null) }

    Button(onClick = {
        coroutineScope.launch {
            data = fetchData()
        }
    }) {
        Text("Fetch Data")
    }

    Text(data ?: "No data yet")
}
```

- با کلیک روی دکمه، داده‌ها در پس‌زمینه دریافت می‌شوند و در صفحه نشان داده می‌شوند.

---

### خلاصه  
1. **`coroutineScope`**: اجرای چند کار همزمان.  
2. **`launch`**: اجرای یک کار پس‌زمینه.  
3. **`rememberCoroutineScope`**: ایجاد Scope هماهنگ با چرخه حیات composable.  

با این ابزارها، می‌توانید کارهای طولانی را در Compose به سادگی و بدون مشکلات اجرا کنید.
