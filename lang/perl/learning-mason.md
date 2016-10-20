1. Core Concepts  
    * A component is a combination of text and Mason-specific markup. The markup sections may contain Perl code or special Mason directives. A component always corresponds to a single file.    
    * Mason parses component by taking the text of a component and translating it into actual Perl code. This Perl code, when executed, creates a new HTML::Mason::Component object.    
    * The markup language used for Mason components contains a simple tag to do in-place substinution of Perl expressions. a way to mark a single line as being a line of Perl, and a set of block tags

          Tag 	Name 	Contains
          <% ... %> 	Substitution 	Perl that is evaluated and sent as output
          % ... 	Perl line 	A single line of Perl code 2
          <%perl> ... </%perl> 	Perl block 	Perl code
          <& ... &> 	Component call 	A call to another component, possibly with arguments
          <%init> ... </%init> 	init block 	Perl code that executes before the main body of the component
          <%args> ... </%args> 	args block 	A component's input argument declarations

2. Escaping substinutions    
    Substitution escaping is indicated with a pipe(|) followed by one or more escape flags placed before the close of tag. Currently, there are three valid escape flags, `h` for HTML entity escaping, `u` for URI escaping, and n for no escaping.   
3. Calling Other Components: <& &> Tags   
    One of the most powerful features in Mason is the ability of one component to execute another, causing the called component's output to appear inside the calling component's output. like this:    

        <html>
        <head>
        <title>The Goober Guide</title>
        </head>
        <body>
        <h1>Welcome to The Goober Guide!</h1>
        <& menu &>
        ...
        </body>
        </html>

4. An alternative to the `<& &>` syntax for calling other components is the `$m->comp()` method. The $m variable contains the `HTML::Mason::Request` object for the current request, and you may use the $m->comp() method in Perl code just as you would use a <& &> in components body. In fact, in the current version of Mason, the <& &> tag is implemented internally with the $m->comp() method. So the following two lines are equivalent to each other.    

        <& menu, width => 640, admin => 1 &>
        % $m->comp('menu', width => 640, admin => 1);

5. **<%init>** blocks. This is one of the most commonly used Mason blocks. The Perl code it contains is run before any other code except for code in **<%once>** or **<%shared>** blocks. And it is run every time the component is called. The <%init> block is typically used for doing things like checking arguments, creating objects, or retrieving data from a database. The variable created here are used in substitutions and perl lines throughout the rest of the component.    

6. **<%args>** blocks.   

        <%args>
        $color
        $size  => 20  # A default value
        @items => ( 1, 2, 'something else' )
        %pairs => ( key1 => 1, key2 => 'value' )
        </%args>

7. **<%filter>** blocks. A <%filter> block is called after a component has finished running. It is given the entire output of the component in the $_ variable, <%filter> can change this variable. For example, the following code uppercases all of the component's output.        

        <%filter>
        s/(\w+)/\U$1/g
        </%filter>

8.  **<%once>** This block is executed whenever the component is loaded into memory. It is executed before any other block(including an <%ini> block). Any variables declared here remain in exsitence until the component is flushed from the memory.  It mainly used for creating database handles or instantiating large, resource-intensive objects.   

9.  **<%cleanup>** is the counterpart to the <%init> block. It is useful if you have created resources that need to be freed.   


          <%init>
          my $resource = get_a_resource( );
          </%init>

          ... do something interesting with that resource

          <%cleanup>
          $resource->dispose;
          </%cleanup>

10. **<%text>** blocks. The contents of this block are output exactly as they are, without any parsing. This is useful if you need to write a component containing text about Mason.   
11. **<%doc>** blocks. This block is intended for use by component authors for documentation purpose. Its contents are completely ignored. In the future Mason may do something more useful with them.    
12. **<%flags> and <%attr>** blocks. As in the <%args> block, the syntax in these blocks looks like Perl, but it is not. First, you cannot end a line with a comma or semicolon. Second, the whole key/value pair must be on a single line. The difference between these two is that the <%flags> block may contain only official Mason flags, which are used to affect the component's behavior. Currently, there is only one flag defined, inherit. This is used to specify the component's parent component. The <%attr> block may contain any keys that you want, as the variables defined in this block are not used by Mason but may be used in your code. Its contents are available by calling the object's attr\(\) method and giving the desired key as the argument.    


          <%flags>
          inherit => '/some/other/component'
          </%flags>

          <%attr>
          color => "I'm so blue"
          size => 'mucho grande'
          </%attr>

          My color: <% $m->base_comp->attr('color') %>


13.  <%def> and <%method>  blocks. These two blocks use a syntax slightly different from any other Mason block because their contents are, in turn, components.   
