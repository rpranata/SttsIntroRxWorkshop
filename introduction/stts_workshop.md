# Pengenalan ReactiveX

Menggunakan [ReactiveX](http://reactivex.io) dalam pengembangan applikasi di Android

## Apakah itu ReactiveX?

Mengutip dari website [ReactiveX]((http://reactivex.io))

> The Observer pattern done right. 

> ReactiveX is a combination of the best ideas from
the Observer pattern, the Iterator pattern, and functional programming

1. **Observer Pattern?**

    Mengutip dari [Wikipedia](https://en.wikipedia.org/wiki/Observer_pattern)

    > The observer pattern is a software design pattern in which an object, called the subject, maintains a list of its dependents, called observers, and notifies them automatically of any state changes, usually by calling one of their methods. 
  
    Jadi yang penting ada 2, `Publisher` (sama dengan `Subject`) dan `Observers`

    Beberapa framework yang ternama contoh nya adalah [EventBus](https://github.com/greenrobot/EventBus) atau [NSNotificationCenter](https://developer.apple.com/documentation/foundation/nsnotificationcenter) di mana ada komponen yang bertindak seperti `Publisher` dan `Observers`. 
  
    `Publisher` merupakan komponen yang mem-publikasikan event, sedangkan `Observers` adalah yang menerima _events_. Dengan memisahkan `Publisher` dengan `Observers`, ini memungkinkan untuk _decoupling_ 2 komponen masing masing.

    ![alt text][event-bus-publis-subscribe]

    Diagram di atas di kutip dari dokumen pengenalan EventBus di mana bisa dilihat lebih jelas hubungan antara `Publisher` dengan 1 (atau lebih) `Subscriber`

2. **Iteration Pattern?**

    Mengutip dari [Wikipedia](https://en.wikipedia.org/wiki/Iterator_pattern)

    > In object-oriented programming, the iterator pattern is a design pattern in which an iterator is used to traverse a container and access the container's elements. The iterator pattern decouples algorithms from containers; in some cases, algorithms are necessarily container-specific and thus cannot be decoupled.

    Ini adalah salah satu struktur dasar bahasa pemograman

    1. Runtutan/Alur/Prosedur
    2. Percabangan
    3. Perulangan

    Contoh dari Iteration Pattern adalah `for..loop`, `for..each` yang sering dipakai untuk mengelola `Collection` (seperti `Array` atau `List`).

    Seperti yang sering dipakai sebagai latihan kuliah awal pemograman:
    ```
    Buat pogram yang menampilkan tampilan seperti berikut di layar monitor:
    *
    **
    ***
    ****
    *****
    ```

3. **Functional Programming?**

    Mengutip dari [Wikipedia](https://en.wikipedia.org/wiki/Functional_programming)

    > In computer science, functional programming is a programming paradigm—a style of building the structure and elements of computer programs—that treats computation as the evaluation of mathematical functions and avoids changing-state and mutable data. It is a declarative programming paradigm, which means programming is done with expressions[1] or declarations[2] instead of statements. In functional code, the output value of a function depends only on the arguments that are passed to the function, so calling a function f twice with the same value for an argument x produces the same result f(x) each time; this is in contrast to procedures depending on a local or global state, which may produce different results at different times when called with the same arguments but a different program state. Eliminating side effects, i.e., changes in state that do not depend on the function inputs, can make it much easier to understand and predict the behavior of a program, which is one of the key motivations for the development of functional programming.

    Merupakan alternatif selain konsep prosedural dalam menulis program. Menurut saya, functional programming itu menekankan idea untuk meminimalisasi _mutable_ state dan **_Higher-order functions_**

    Higher-order functions berarti _function/method_ sebagai first class citizen (sejajar dengan _class_). Function bisa menerima function lain sebagai argumen dan bisa mengembalikan function sebagai _return value_

## Extensi ReactiveX

  ReactiveX mempunyai beberapa extensi yang dapat dipakai untuk mengembangkan applikasi Android
  
  - [RxJava](https://github.com/ReactiveX/RxJava)
  - [RxKotlin](https://github.com/ReactiveX/RxKotlin)
  - [RxAndroid](https://github.com/ReactiveX/RxAndroid)



[View Model and Live Data](https://medium.com/google-developers/viewmodels-and-livedata-patterns-antipatterns-21efaef74a54 )

# Selanjutnya

  [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)

[event-bus-publis-subscribe]: EventBus-Publish-Subscribe.png "Event Bus Observer Pattern Diagram"
