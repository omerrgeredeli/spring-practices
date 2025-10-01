Assignment: “Multithreading & Concurrency Playground”
🎯 Amaç

Thread ve Runnable ile temel çoklu iş parçacığı yönetimini öğrenmek

ExecutorService ve Future ile görev planlama

CompletableFuture ile asenkron zincirleme

Race condition ve çözümü (synchronized, Lock)

Deadlock senaryosu ve önlenmesi

🗂️ Senaryo

Bir banka simülasyonu yapacaksın:

Bankada bir ortak hesap (balance) var.

Farklı thread’ler bu hesaptan para çekiyor veya yatırıyor.

Thread’ler Runnable ve ExecutorService üzerinden çalışacak.

Bazı işlemler Future veya CompletableFuture ile asenkron olacak.

Yanlış senkronizasyon yapılmazsa race condition oluşacak (bakiyeler hatalı olacak).

synchronized ve ReentrantLock kullanarak race condition çözülecek.

Ayrıca yanlış kilit sırasıyla deadlock örneği göstereceksin ve sonra önleyeceksin.
