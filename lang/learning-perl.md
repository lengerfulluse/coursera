*  perl程序不需要变量声明部分。
*  perl语言大部分语句都是表达式后面紧接着一个分号；
*  perl并不限于使用ascii字符串作为变量名。但需要启用utf8编译指令。
*  perl支持双目赋值操作符。
*  双引号可以实现转移，标量变量内插。 如$barney = "fred ate a $meal";
*  把<STDIN>放在程序中希望返回标量值得位置上。 $line = <STDIN>;
*  undef当数字使用时就是零；字符串使用是就是空字符串；可以使用defined函数来判断是否到达end of file。

    $madonna = <STDIN>;
    if (defined($madonna)) {
      print "The input was $madonna";
    }

*  特殊的数组索引： 如果你对索引值超过数组git 尾端的元素进行复制，数组将会根据需要自动扩大，只要有可用的内存分配给perl，数组的长度是没有上限的。需要创建增补的元素，他们的默认值为undef的。负数数组索引值是从数组尾端往回计数。arr[-1]是指最后一个元素。
*  列表直接变量，可以用圆括号内用逗号隔开的一串数据。("fred", 4.5)，列表中的数据也可以是比标量，表达式。
*  范围操作符，.. 从左边的数字计数到右边，以产生一连串的数字。
*  _qw_ 关键词用来表示引用字符串。比如`qw(fred barney betty wilma dino )`，perl把其当成是单引号‘’引用来处理。
*  列表的赋值可以像标量一样赋值，且数组中的元素可以是变量。
*  如果要在某个标量变量后直接写左方括号，你需要先将这个方括号隔开，才不至于被识别为数组引用的一部分。做法如下：   

    @fred = qw(eating rocks is wrong);
    $fred = "right";
    print "this is $fred[3]\n"  # this is wrong.
    print "this is ${fred}[3]\n"  # this is right[3].
    print "this is $fred"."[3]\n"  # this is right[3].

*  foreach循环中的控制变量不是列表元素的复制品，实际上它是列表元素本身。也就是说，在循环中修改了控制变量的值，也同时修改了这个列表元素。perl最喜欢的默认变量便是$\_:   

    foreach(1..10) {
      print "i can count to $\_";
    }

*  reverse操作符会读取列表的值，并按照相反的次序返回该列表。但它不会修改传进来的参数。就像Java中的replace操作一样。
*  each 操作符，会返回数组中下一个元素所对应的两个值--该元素的索引以及该元素的值：   

    my @rocks = qw/ bedrock slate rubble granite /;
    while (my( $index, $value ) = each @rocks ) {
        say "$index: $value";
    }

* chomp针对列表上下文输入去除所有换行符的方法：  

    chomp(@lines = <STDIN);
