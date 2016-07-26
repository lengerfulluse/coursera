#### 哈希与正则表达式     

1. 砖石操作符<>是行输入操作符的特例。不过其并不是从键盘取得输入，而是从用户指定的位置读取：   

        while ( defined($line = <>)) {
          chomp($line);
          print "It was $line that i saw!\n";
        }
2. 哈希可以通过一般的赋值语法来复制：`my %new_hash = %old_hash;`，也可以直接根据现有哈希转换得到反序哈希： `my %inverse_hash = reverse %any_hash;`   
3. 胖箭头。将列表赋值到哈希时常常发现列表中键值对不容易区分。perl通过胖箭头=>来区分使其更加清晰。且使用胖箭头的时候可以省略键的引号，左边的部分会被自动引起。     

        my %last_name = (
            'fred' => 'fintstone',
            'dino' => 'undef',
          );

4.  perl有很多函数可以一次处理整个哈希。keys和values函数。  
        my %hash = ('a' => 1, 'b' => 2, 'c' => 3);
        my @k = keys %hash;
        my @v = values %hash;

5. 可以通过each函数迭代整个哈希。每次对同一个哈希调用次函数，它就返回下一组键值对。通常唯一适合使用each的地方就是while循环中：   

        while(($key, $value) = each %hash) {
            print "$key => $value\n";
        }

6. 常见的perl正则表达式。Unicode属性的，如`/\p{Space}/`，`/\p{Digit}/`，当然还有元字符。\. 等。关于perl正则表达式的更详细的教程可以参考Tom Phoenix著的perl语言入门的正则表达式章节。      
