#!/usr/bin/perl
# clockdrift - compare another system's clock with this one
use strict;
use Socket;
use CGI qw(:all);

$| = 1;

print header(), "HERE\n";

socket(MsgBox, PF_INET, SOCK_DGRAM, getprotobyname("udp"))
    or die "socket: $!";
my $addr = sockaddr_in("1234", inet_aton("0.0.0.0"));

bind(MsgBox,$addr)
	or print "bin: $!";

defined(my $src = recv(MsgBox, my $ptime, 4, 0))
    or print "recv: $!";

print "got\n";
my ($port, $ipaddr) = sockaddr_in($src);

my $host = gethostbyaddr($src, AF_INET);
defined(send(MsgBox, 0, 0, $src))
    or print "send: $!";

print inet_ntoa($ipaddr)." - $host\n";


