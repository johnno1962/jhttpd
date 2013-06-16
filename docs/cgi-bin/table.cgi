#!/usr/bin/perl

$CGI=$ENV{'SCRIPT_NAME'};
if( $CGI eq "" ) { $CGI="/cgi-bin/table.cgi"; }

select(STDERR); $|=1;
select(STDOUT); $|=1;


# sub ReadParse {
  local (*in) = @_ if @_;  local ($i, $loc, $key, $val);
  if ($ENV{'REQUEST_METHOD'} eq "GET") {
    $in = $ENV{'QUERY_STRING'};
  } elsif ($ENV{'REQUEST_METHOD'} eq "POST") {
    read(STDIN,$in,$ENV{'CONTENT_LENGTH'});
      warn "table.cgi reading $ENV{'CONTENT_LENGTH'} (== @{[length $in]}?)\n";
#      warn "read>>$in<<";
  }
  @in = split(/&/,$in);
  undef %in;
  foreach $i (0 .. $#in) {
    $in[$i] =~ s/\+/ /g;
    ($key, $val) = split(/=/,$in[$i],2); 
    $key =~ s/%(..)/pack("c",hex($1))/ge;
   

 $val =~ s/%(..)/pack("c",hex($1))/ge;
    $in{$key} .= "\0" if (defined($in{$key}));
    $in{$key} .= $val;  }
#  return 1;
#}

#&ReadP

# warn "here...  @{[%in]}";
print "Content-type: text/html\n\n" unless $ENV{DOCUMENT_ZIPPED};

print "<PRE>";
foreach my $k  (sort keys %ENV) {
#    print "$k: >$ENV{$k}<\n";
}
print "</PRE>";


$COMMAND=$in{'COMMAND'};


# ---------------------
# Output Opening Screen
# ---------------------
if ($COMMAND eq '') {
    local $^W=0;
print <<EOF;
<HTML>
<HEAD>  <TITLE>Table Generator</TITLE>  </HEAD>
<BODY>
<TABLE BORDER=3 WIDTH=100% CELLSPACING=5>
<TR>
 <TD WIDTH=50% ALIGN=CENTER><H1>Table</H1></TD>
 <TD WIDTH=50% ALIGN=CENTER><H1>Generator</H1></TD>
</TR>
<TR>
 <TD ALIGN=CENTER>By Matt Kruse</TD>
 <TD ALIGN=CENTER>6/29/95 -- Version 1.1</TD>
</TR>
</TABLE>
<HR WIDTH=75%>
<HR WIDTH=25%>
<FONT SIZE="+2">W</FONT>elcome to Table Generator, an easy-to-use 
utility that will make basic tables quickly and easily.  You must have 
a view that is capable of displaying HTML 3.0-compliant tables, like 
Netscape.  Not all features of tables are supported in this program,
so some manual editing may be needed if you wish to create complex
tables.<P>
Enter the dimensions of the table you wish to create:<P>

<FORM ACTION="$CGI" METHOD=GET>
<INPUT TYPE=HIDDEN NAME="COMMAND" VALUE="MAKEFORM">
<B>Width:</B> <INPUT NAME="WIDTH" SIZE=2 MAXLENGTH=2><BR>
<B>Height:</B> <INPUT NAME="HEIGHT" SIZE=2 MAXLENGTH=2><P>

<B>Default alignment of cells will be:</B><BR>
<INPUT TYPE="RADIO" NAME="DEFAULTALIGN" VALUE="LEFT"> Left  
<INPUT TYPE="RADIO" NAME="DEFAULTALIGN" VALUE="CENTER" CHECKED> Center  
<INPUT TYPE="RADIO" NAME="DEFAULTALIGN" VALUE="RIGHT"> Right  <P>
<INPUT TYPE="SUBMIT" VALUE="Create Entry Table">
</FORM>
EOF
&Footer;
}

# ---------------------------------
# Create the table with input areas
# ---------------------------------
if ($COMMAND eq 'MAKEFORM') {             #create an entry form
$WIDTH=$in{'WIDTH'};
$HEIGHT=$in{'HEIGHT'};
if ($in{'DEFAULTALIGN'} eq 'LEFT') {$LEFT="CHECKED";}
if ($in{'DEFAULTALIGN'} eq 'CENTER') {$CENTER="CHECKED";}
if ($in{'DEFAULTALIGN'} eq 'RIGHT') {$RIGHT="CHECKED";}

print <<EOF;
<HTML>
<HEAD>  <TITLE>Table Entry Form</TITLE>  </HEAD>
<BODY>
<H1 ALIGN=CENTER>Table Entry Form</H1>
<FONT SIZE="+2">U</FONT>se the entry areas below to put data into your 
table that you wish to create.<P>
Use the radio buttons under the cell to change the alignment of 
the specific cells to Left, Center, or Right.  By making a certain row 
or column a "Header" rather than "Data", it will be be bold by default.<P>
The checkboxes between cells allow you to make data or header cells 
that span more than one column.  By clicking on the checkbox between 
two cells, they become one cell.  The data entered in the left cell is 
what will be entered in the larger cell, and anything in the right 
cell will be ignored.  You may make any cell as large as you wish.  
For example, if you connect all the boxes, the data in the left-most 
cell will take up a whole row, and no other data will appear.<P>

<FORM METHOD=POST ACTION="$CGI">
<INPUT TYPE="HIDDEN" NAME="COMMAND" VALUE="MAKETABLE">
<INPUT TYPE="HIDDEN" NAME="WIDTH" VALUE="$WIDTH">
<INPUT TYPE="HIDDEN" NAME="HEIGHT" VALUE="$HEIGHT">
Border Type: 
<SELECT NAME="BORDER" SIZE=1>
<OPTION>NONE
<OPTION>BORDER=1
<OPTION SELECTED>BORDER=3
<OPTION>BORDER=5
<OPTION>BORDER=10
</SELECT>   
<INPUT TYPE="CHECKBOX" NAME="WIDTH100" VALUE="1" CHECKED>Add the 
     "Width=100%" tag to the Table.<BR>
<B>Caption:</B><INPUT NAME="CAPTION" SIZE=30>
<INPUT TYPE="RADIO" NAME="CAPTIONALIGN" VALUE="TOP" CHECKED> Top 
<INPUT TYPE="RADIO" NAME="CAPTIONALIGN" VALUE="BOTTOM"> Bottom 
<INPUT TYPE="CHECKBOX" NAME="CAPTIONBOLD" VALUE="1" CHECKED> <B>Bold</B><BR>
<I>(If Caption is left empty, no caption will be in the table output)</I>
<P>
<INPUT TYPE="SUBMIT" VALUE="Create This Table">
<TABLE BORDER=2>
EOF

#output top row headers
print "<TR>\n";
print " <TH></TH>\n";
for ($i=1;$i<=$WIDTH;$i++) {
   print " <TH>Column $i</TH>\n";
   if ($i != $WIDTH) {print " <TH></TH>\n";}
   }
print "</TR>\n\n";
print "<TR>\n";
print " <TH>Row/Col Type</TH>\n";
for ($i=1;$i<=$WIDTH;$i++) {
print <<EOF;
  <TD ALIGN=LEFT>
    <INPUT TYPE="RADIO" NAME="COLTYPE${i}" VALUE="TH">Header<BR>
    <INPUT TYPE="RADIO" NAME="COLTYPE${i}" VALUE="TD" CHECKED>Data
  </TD>
EOF
   if ($i != $WIDTH) {print " <TD></TD>\n";}
   }
print "</TR>\n\n";

#create all entry areas
for ($row=1;$row<=$HEIGHT;$row++) {
 print <<EOF;
 <TR>
  <TD ALIGN=LEFT>
    <INPUT TYPE="RADIO" NAME="ROWTYPE${row}" VALUE="TH">Header<BR>
    <INPUT TYPE="RADIO" NAME="ROWTYPE${row}" VALUE="TD" CHECKED>Data
  </TD>
EOF
 for ($col=1;$col<=$WIDTH;$col++) {
     local $^W = 0;
  print <<EOF;
  <TD ALIGN=CENTER>
  <INPUT NAME="R${row}C${col}" SIZE=10><BR>
  <INPUT TYPE="RADIO" NAME="R${row}C${col}ALIGN" VALUE="LEFT" $LEFT>
  <INPUT TYPE="RADIO" NAME="R${row}C${col}ALIGN" VALUE="CENTER" $CENTER>
  <INPUT TYPE="RADIO" NAME="R${row}C${col}ALIGN" VALUE="RIGHT" $RIGHT>
  </TD>
EOF
  if ($col != $WIDTH) {print <<EOF;
  <TD ALIGN=CENTER>
  <INPUT TYPE="CHECKBOX" NAME="R${row}C${col}LINK" VALUE="1">
    <BR><B>&lt;-&gt;</B>
  </TD>
EOF
    }
  } #end of columns
 print "</TR>\n";
 } #end of rows

print <<EOF;
</TABLE>
</FORM>
<P>
EOF
&Footer;
}

# --------------------------------------
# Output the table and table source code
# --------------------------------------
if ($COMMAND eq 'MAKETABLE') {
$WIDTH=$in{'WIDTH'};
$HEIGHT=$in{'HEIGHT'};
$CAPTION=$in{'CAPTION'};
$CAPTIONALIGN=$in{'CAPTIONALIGN'};
$CAPTIONBOLD=$in{'CAPTIONBOLD'};
$BORDER=$in{'BORDER'}; if ($BORDER eq 'NONE') {$BORDER='';}
if ($in{'WIDTH100'}) { $WIDTH100="WIDTH=100%"; }

print <<EOF;
<HTML>
<HEAD>  <TITLE>Table Output</TITLE>  </HEAD>
<BODY>
<H1 ALIGN=CENTER>Table Output</H1>
View the source of this page to see the code for this table.  You can 
then save it to a file, or copy and paste it into the document you 
wish to use this with.<BR>
<HR WIDTH=75%>
<HR WIDTH=25%>
<H3>Sample Table:</H3>

<!---------------CUT HERE------------------->
<TABLE $BORDER $WIDTH100>
EOF

if ($CAPTION) {print "<CAPTION ALIGN=$CAPTIONALIGN>\n";
   if ($CAPTIONBOLD) {print "<B>";}
   print "$CAPTION";
   if ($CAPTIONBOLD) {print "</B>";}
   print "\n</CAPTION>\n";
   }

for ($row=1;$row<=$HEIGHT;$row++) {
 print "<TR>\n";
 for ($col=1;$col<=$WIDTH;$col++) {
   if (($in{"ROWTYPE$row"} eq 'TH' ) || ($in{"COLTYPE$col"} eq 'TH' ))
      {$TYPE='TH';}
      else {$TYPE='TD';}
    $LINK="R${row}C${$col-1}LINK";
    if ($in{$LINK} && $in{$LINK} eq '1') {next;}
    $COLSPAN=1;
    for ($i=$col;$i<=$WIDTH;$i++) {
      $LINK="R${row}C${i}LINK";
      if ($in{$LINK} && $in{$LINK} ne '1') {last;}
      if ($in{$LINK} && $in{$LINK} eq '1') {$COLSPAN++;}
      }
    $POSITION="R${row}C${col}";
    $ALIGN="${POSITION}ALIGN";
    print "<${TYPE} ALIGN=$in{$ALIGN} COLSPAN=$COLSPAN>$in{$POSITION}</$TYPE>\n";
    } #end of columns
 print "</TR>\n";
 } #end of rows

print "</TABLE>\n";
print "<!---------------CUT HERE------------------>\n";
print "<P>\n";

print <<EOF;
<P>
EOF
&Footer;
}

sub Footer {
print <<EOF;
<HR WIDTH=75%>
<ADDRESS><P ALIGN=CENTER>
Table Generator was written by Matt Kruse (mkruse\@saunix.sau.edu).  It 
is free software as long as this footer is maintained in the script.  
Distribute and modify as you wish.  Written in Perl 4.036 and tested 
using Netscape version 1.1.  Comments and suggestions welcome.<BR>

Run number: @{[++$times]}

</P></ADDRESS>
<HR WIDTH=25%>
</BODY>
</HTML>
EOF
}

warn __FILE__." done";
