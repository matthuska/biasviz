<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>BiasViz</title>
<link href="style.css" type="text/css" rel="stylesheet" />
</head>
<body>
<div id="content">
<h1>BiasViz: Protein Amino Acid Bias Analysis Applet</h1>
<h2>Summary</h2>
<p>This applet can be used to look at the composition of amino acids within a sliding window given a multiple sequence alignment. The window size and amino acid(s) of interest can be modified and the results are displayed in real time, allowing the user to tweak both of these parameters to their liking. The values can also be downloaded as a CSV file which allows further visualization in a separate program such as Microsoft Excel.</p>
<h2>Enter Alignment</h2>
<p>Fasta format only please. <a href="http://www.java.com/en/download/">Java</a> plug-in required.</p>
<form enctype="multipart/form-data" action="applet.php" method="post">
<p>
<label for="datafile">Upload a file:</label>
</p>
<input type="file" name="datafile" size="40" />
<p> or </p>
<p>
<label for="alignment">Paste an alignment:</label>
</p>
<textarea id="alignment" name="alignment" rows="15" cols="90">
>Ebf3_mouse
--------------MFPAQDALPRGGLHLKEEPL-LPSSLGSVRSWMQSAGILDSNTAAQ
--SGVGLARAHFEKQPPSNLRKSNFFHFVLAMYDRQGQPVEVERTAFIDFVEKDREPGTE
KTNNGIHYRLRLVYNNGLRTEQDLYVRLIDSMSKQAIIYEGQDKNPEMCRVLLTHEIMCS
RCCDRKSCGNRNETPSDPVIIDRFFLKFFLKCNQNCLKNAGNPRDMRRFQVVVSTTVSVD
GHVLAVSDNMFVHNNSKHGRRARRLDPSEA----------------------------AT
PCIKAISPGEGWTTGGATVIIIGDNFFDGLQVVFGNVLLWSELITPHAIRVQTPPRHIPG
VVEVTLSYKSKQFCKGAPGRFVYT-ALNEPTIDYGFQRLQKVIPRHPGDPERLPKEVLLK
RAADLAEALYGVPSSNQELLLKRAADVAEALYSAPRAPAPLGPLAP---SHPHPAVVGIN
AFSSPLAIAVGDTTP--EP-GYARS-CGSASPR-FAPSPGSQQSSYGSGLGAGL------
-------------------------VMPSSPPLAAAS--SMSLPAAAPTTSVFSFSPVNM
IC-AVKQRSAFAPVLRPPSSPSQACPRAHRE-------GLPDQP--FEDTDKFHSAARG-
------LQGLAYS
>Ebf4_human
--------------MFPAQDALPRSGLNLKEEPL-LPAGLGSVRSWMQGAGILDASTAAQ
--SGVGLARAHFEKQPPSNLRKSNFFHFVLAMYDRQGQPVEVERTAFIDFVEKDREPGAE
KTNNGIHYRLRLVYNNGLRTEQDLYVRLIDSMSKQAIIYEGQDKNPEMCRVLLTHEIMCS
RCCDRKSCGNRNETPSDPVIIDRFFLKFFLKCNQNCLKNAGNPRDMRRFQVVVSTTVSVD
GHVLAVSDNMFVHNNSKHGRRARRLDPSEA----------------------------AT
PCIKAISPGEGWTTGGATVIVIGDNFFDGLQVVFGNVLVWSELITPHAIRVQTPPRHIPG
VVEVTLSYKSKQFCKGCPGRFVYT-ALNEPTIDYGFQRLQKVIPRHPGDPERLPKEVLLK
RAADLAEALYGVPGSNQELLLKRAADVAEALYSTPRAPGPLAPLAP---SHPHPAVVGIN
AFSSPLAIAVGDATPGPEP-GYARS-CSSASPRGFAPSPGSQQSGYGGGLGAGLGGYGA-
PGVAGLGVPGSPSFLNGSTATSPFAIMPSSPPLAAAS--SMSLPAAAPTTSVFSFSPVNM
IS-AVKQRSAFAPVLRPPSSPPQACPRAHGE-------GLPDQS--FEDSDKFHSPARG-
------LQGLAYS
>Ebf4_mouse
--------------MFPAQDALPRGGLHLKEEPL-LPSSLGSVRSWMQSAGILDSNTAAQ
--SGVGLARAHFEKQPPSNLRKSNFFHFVLAMYDRQGQPVEVERTAFIDFVEKDREPGTE
KTNNGIHYRLRLVYNNGLRTEQDLYVRLIDSMSKQAIIYEGQDKNPEMCRVLLTHEIMCS
RCCDRKSCGNRNETPSDPVIIDRFFLKFFLKCNQNCLKNAGNPRDMRRFQVVVSTTVSVD
GHVLAVSDNMFVHNNSKHGRRARRLDPSEA----------------------------AT
PCIKAISPGEGWTTGGATVIIIGDNFFDGLQVVFGNVLLWSELITPHAIRVQTPPRHIPG
VVEVTLSYKSKQFCKGAPGRFVYT-ALNEPTIDYGFQRLQKVIPRHPGDPERLPKEVLLK
RAADLAEALYGVPSSNQELLLKRAADVAEALYSAPRAPAPLGPLAP---SHPHPAVVGIN
AFSSPLAIAVGDTTP--EP-GYARS-CGSASPR-FAPSPGSQQSSYGSGLGAGLGSYG--
------------------------A--PGVTGLGVPG--SPSFLNGSTATSPF-------
----AKER------LRPCAAPTQFP----IA-------GLP------------QSPQRG-
------ASRPAF-
>Ebf4_dog
--------------MFPAQDTLPRGGLNLKEEPL-LPAGLGSVRSWMQGAGILDASTAAQ
--SGVGLARAHFEKQPPSNLRKSNFFHFVLAMYDRQGQPVEVERTAFIDFVEKDREPGAE
KTNNGIHYRLRLVYNNGLRTEQDLYVRLIDSMSKQAIIYEGQDKNPEMCRVLLTHEIMCS
RCCDRKSCGNRNETPSDPVIIDRFFLKFFLKCNQNCLKNAGNPRDMRRFQVVVSTTVSVD
GHVLAVSDNMFVHNNSKHGRRARRLDPSEA----------------------------AT
PCIKAISPGEGWTTGGATVIVIGDNFFDGLQVVFGNVLVWSELITPHAIRVQTPPRHIPG
VVEVTLSYKSKQFCKGAPRRFVYT-PLNEPTIDYGFQRLQKAIPRHPGDPERLPKEVLLK
RAADLAEALYGVPSSNQELLLKRAADVAEALYSAPRAPAPLGPLAP---SHPHPAVVGIN
AFSSPLAIAVGDATPGPEP-GYARS-CGSASPRGFAPSPGSQQSGYGSGLGAGL------
------------------------A--PRWP-LPPPCP-SRPLPPP--------------
----------------PASSPSRLST----------------------------------
-------------
>Ebf3_opossum
--------------MFSMQDSLPRGGLSLKEEP--LPAGLGSVRSWMQGTGILDATTAAQ
--SGVGLARAHFEKQPPSNLRKSNFFHFVLAMYDRQGQPVEIERTAFIDFVEKEREPSGE
RTNNGIHYRLQLLYSNGIRTEQDLYVRLIDSMSKQAIIYEGQDKNPEMCRVLLTHEIMCS
RCCDKKSCGNRNETPSDPVIIDRFFLKFFLKCNQNCLKNAGNPRDMRRFQVVVSTTVSVD
GHVLAVSDNMFVHNNSKHGRRARRLDPSE-----------------------------AT
PCIKAISPSEGWTTGGATVIVIGDNFFDGLQVVFGTMLVWSELITPHAIRVQTPPRHIPG
VVEVTLSYKSKQFCKGAPGRFVYT-ALNEPTIDYGFQRLQKVIPRHPGDPERLPKEVLLK
RAADLVEALYGMPHSNQDLIIKRAADIAEALYSVPRTPSQLGSLAP---GHPHTAMMGIN
SFGSQLAVNIGDSTQGADQ-GYSRN-TSSVSPRGYVPSSTPQQSSYSS-ITSSINGYGA-
TGMAGLGVPSSPSFLNGSTANSPYAIMPSSPPLAAS---SISLPAAAPTTSVFSFSPVNM
IS-AVKQKSAFAPVVRPPGSPPPACTSTNGS-------ALQ--------------AMSG-
------LLIPPM-
>Ebf2_mouse
---------------MFGIQDTLGRGPALKDKS--LGAEMDSVRSWVRNVGVVDANVAAQ
--SGVALSRAHFEKQPPSNLRKSNFFHFVLALYDRQGQPVEIERTAFVDFVENDKEQGNE
KTNNGTHYKLQLLYSNGVRTEQDLYVRLIDSVTKQPIAYEGQNKNPEMCRVLLTHEVMCS
RCCEKKSCGNRNETPSDPVIIDRFFLKFFLKCNQNCLKTAGNPRDMRRFQVVLSTTVNVD
GHVLAVSDNMFVHNNSKHGRRARRLDPSE-----------------------------AT
PCIKAISPSEGWTTGGAMVIIIGDNFFDGLQVVFGTMLVWSELITPHAIRVQTPPRHIPG
VVEVTLSYKSKQFCKGAPGRFIYT-ALNEPTIDYGFQRLQKVIPRHPGDPERLAKEMLLK
RAADLVEALYGTPHNNQDIILKRAADIAEALYSVPRNPSQIPALSS---SPAHSGMMGIN
SYGSQLGVSISESTQGNNQ-GYIRN-TSSISPRGYSSSSTPQQSNYST-SSNSMNGYSN-
VPMANLGVPGSPGFLNGSPTGSPYGIM-------------SSSPTVGSSSTSSILPFSSS
VFPAVKQKSAFAPVIRPQGSPSPACSSGNGN-------GFR--------------AMTG-
------LVVPPM-
>Ebf2_dog
---------------MFGIQDTLGRGPALKEKS--LGAEMDSVRSWVRNVGVVDANVAAQ
--SGVALSRAHFEKQPPSNLRKSNFFHFVLALYDRQGQPVEIERTAFVDFVENDKEQGNE
KTNNGTHYKLQLLYSNGVRTEQDLYVRLIDSVTKQPIAYEGQNKNPEMCRVLLTHEVMCS
RCCEKKSCGNRNETPSDPVIIDRFFLKFFLKCNQNCLKTAGNPRDMRRFQVVLSTTVNVD
GHVLAVSDNMFVHNNSKHGRRARRLDPSE-----------------------------AT
PCIKAISPSEGWTTGGAMVIIIGDNFFDGLQVVFGTMLVWSELITPHAIRVQTPPRHIPG
VVEVTLSYKSKQFCKGAPGRFIYT-ALNEPTIDYGFQRLQKVIPRHPGDPERLAKEMLLK
RAADLVEALYGTPHNNQDIILKRAADIAEALYSVPRNPSQIPALSS---SPAHSGMMGIN
SYGTQLGVSISESTQGNNQ-GYIRN-TSSISPRGYSSSSTPQQSNYST-SSNSMNGYSN-
VPMANLGVPGSPGFLNGSPTGSPYGIM-------------SSSPTVGSSSTSSILPFSSS
VFPAVKQKSAFAPVIRPQGSPSPACSSGNGN-------GFR--------------AMTG-
------LVVPPM-
>Ebf2_human
---------------MFGIQDTLGRGPTLKEKS--LGAEMDSVRSWVRNVGVVDANVAAQ
--SGVALSRAHFEKQPPSNLRKSNFFHFVLALYDRQGQPVEIERTAFVDFVENDKEQGNE
KTNNGTHYKLQLLYSNGVRTEQDLYVRLIDSVTKQPIAYEGQNKNPEMCRVLLTHEVMCS
RCCEKKSCGNRNETPSDPVIIDRFFLKFFLKCNQNCLKTAGNPRDMRRFQVVLSTTVNVD
GHVLAVSDNMFVHNNSKHGRRARRLDPSE-----------------------------AT
PCIKAISPSEGWTTGGAMVIIIGDNFFDGLQVVFGTMLVWSELITPHAIRVQTPPRHIPG
VVEVTLSYKSKQFCKGAPGRFIYT-ALNEPTIDYGFQRLQKVIPRHPGDPERLAKEMLLK
RAADLVEALYGTPHNNQDIILKRAADIAEALYSVPRNPSQLPALSS---SPAHSGMMGIN
SYGSQLGVSISESTQGNNQ-GYIRN-TSSISPRGYSSSSTPQQSNYST-SSNSMNGYSN-
VPMANLGVPGSPGFLNGSPTGSPYGIM-------------SSSPTVGSSSTSSILPFSSS
VFPAVKQKSAFAPVIRPQGSPSPACSSGNGN-------GFR--------------AMTG-
------LVVPPM-
>Ebf3_chicken
---------------MFGIQDTLGRGPVLKDKS--LGSEMDSVRSWVRNVGVVDANVAAQ
--SGVALSRAHFEKQPPSNLRKSNFFHFVLALYDRQGQPVEIERTAFVDFVENDKEQGNE
KTNNGTHYKLQLLYSNGVRTEQDLYVRLIDSVTKQPITYEGQNKNPEMCRVLLTHEVMCS
RCCEKKSCGNRNETPSDPVIIDRFFLKFFLKCNQNCLKTAGNPRDMRRFQVVLSTTVNVD
GHVLAVSDNMFVHNNSKHGRRARRLDPSE-----------------------------AT
PCIKAISPSEGWTTGGAMVIIIGDNFFDGLQVVFGTMLVWSELITPHAIRVQTPPRHIPG
VVEVTLSYKSKQFCKGAPGRFIYT-ALNEPTIDYGFQRLQKVIPRHPGDPERLAKEMLLK
RAADLVEALYGTPHNNQDIILKRAADIAEALYSVPRNPAQIPALSS---SPAHSSMMGIN
SYGSQLGVSISESTQGNNQ-GYIRN-TSSISPRGYSSSSTPQQSNYST-SSNSMNGYSN-
VPMSNLGVPGSPGFINGSPTGSPYGIM-------------SSSPTVGSSSTSSILPFSSS
VFPAVKQKSAFAPVIRPQGSPSPACSSGNGN-------GFR--------------AMTG-
------LVVPPM-
>Prot_opossum
---------------MFGIQDTLGRGPALKEKA--LGAEMDSVRSWVRNVGVVDANVAAQ
--SGVALSRAHFEKQPPSNLRKSNFFHFVLALYDRQGQPVEIERTAFVDFVENDKEQGNE
KTNNGTHYKLQLLYSNGVRTEQDLFVRLIDSVTKQPIAYEGQNKNPEMCRVLLTHEVMCS
RCCDKGSCTHRVQAPTDSENVEKFFLKFFLKCNQNCLKTAGNPRDMRRFQVVLSTTVNVD
GHVLAVSDNMFVHNNSKHGRRARRLDPSE-----------------------------AT
PCIKAISPSEGWTTGGAMVIIIGDNFFDGLQVVFGTMLVWSELITPHAIRVQTPPRHIPG
VVEVTLSYKSKQFCKGAPGRFIYT-ALNEPTIDYGFQRLQKVIPRHPGDPERLAKEMLLK
RAADLVEALYGTPHNNQDIILKRAADIAEALYSVPRNPSQIPALSS---SPAHSGMMGIN
SYGSQLGVSISESTQGNNQVGYIRN-TSSISPRGYSSSSTPQQSNYST-SSNSMNGYSN-
VPMANLGVPGSPGFLNGSPTGSPYG-----------------------------------
---TVKQKSAFAPVIRPQGSPSPACSSGNGN-------GFR--------------AMTG-
------LVVPPM-
>Ebf2_danio
--------------MFESQDHSIRTITELKVRT--FEEEMDPVRSWVRNVGVIDANIAAQ
--SGVALSRAHFEKQPPSNLRKSNFFHFVLALYDRNGQPVEVERTSFVDFVEQDK--TGE
KTNNGTHYKLQLLYSNGVRTEQDLYARLIDSVTKQPISYEGQNKNPEMCRVLLTHEVMCS
RCCEKKSCGNRNETPSDPVIIDRFFLKFFLKCNQNCLKTAGNPRDMRRFQVVLSTTVCVD
GPVLAISDNMFVHNNSKHGRRSRRMDPNETV--------------------ENNME-YAT
PCIKAISPSEGWTTGGAMVIVIGENFFDGLQVVFGSMLVWSELITPHAIRVQTPPRHIPG
VVEVTLSYKSKQFCKGAPGRFIYT-ALNEPTIDYGFQRLQKLIPRHPGDPDKLAKEMLLK
RAADVVESLYGNTTSNQDMLLKRAADIAEALYSVPRPHSQLQAMPS---SPVHGSVMGLS
SYPTQLGVSIGEPGQTSGQ-GYTRN-SSSLSPRGYPSSSTPQQSAYGS-NGGMS--YGA-
VPMSSLGVSGSPGFNSASPNSSPYAIMPS------------SP--PGSSSSSS-LLPFSS
FPSSTKQKSAFAPVLRPQGSPSPVCQTSGGT-------SFR--------------AMTG-
------LVVPPM-
>Ebf1_dog
--------------MFGIQESIQRSGSSMKEEP--LGSGMNAVRTWMQGAGVLDANTAAQ
--SGVGLARAHFEKQPPSNLRKSNFFHFVLALYDRQGQPVEIERTAFVGFVEKEKEANSE
KTNNGIHYRLQLLYSNGIRTEQDFYVRLIDSMTKQAIVYEGQDKNPEMCRVLLTHEIMCS
RCCDKKSCGNRNETPSDPVIIDRFFLKFFLKCNQNCLKNAGNPRDMRRFQVVVSTTVNVD
GHVLAVSDNMFVHNNSKHGRRARRLDPSE-----------------------------AT
PCIKAISPSEGWTTGGATVIIIGDNFFDGLQVIFGTMLVWSELITPHAIRVQTPPRHIPG
VVEVTLSYKSKQFCKGTPGRFIYT-ALNEPTIDYGFQRLQKVIPRHPGDPERLPKEVILK
RAADLVEALYGMPHNNQEIILKRAADIAEALYSVPRNHSQLPALTN---TSVHAGMMGVN
SFSGQLAVNVSEASQATNQ-GFTRN-SSSVSPHGYVPSTTPQQTNYNS-VSTSMNGYGT-
AAMSNLG--GSPTFLNGSAANSPYATV---------------------------------
-----KQKSAFAPVVRPQTSPPPTCTSTNGN-------SLQAIS---------GMIVPPM
-------------
>Ebf1_opossum
--------------MFGIQESIQRSGSSMKEEP--LGSGMNAVRTWMQGAGVLDANTAAQ
--SGVGLARAHFEKQPPSNLRKSNFFHFVLALYDRQGQPVEIERTAFVGFVEKEKEANSE
KTNNGIHYRLQLLYSNGIRTEQDFYVRLIDSMTKQAIVYEGQDKNPEMCRVLLTHEIMCS
RCCDKKSCGNRNETPSDPVIIDRFFLKFFLKCNQNCLKNAGNPRDMRRFQVVVSTTVNVD
GHVLAVSDNMFVHNNSKHGRRARRLDPSE-----------------------------AT
PCIKAISPSEGWTTGGATVIIIGDNFFDGLQVIFGTMLVWSELITPHAIRVQTPPRHIPG
VVEVTLSYKSKQFCKGTPGRFIYT-ALNEPTIDYGFQRLQKVIPRHPGDPERLPKEVILK
RAADLVEALYGMPHNNQEIILKRAADIAEALYSVPRNHNQLPALAN---TSVHAGMMGVN
SFSGQLAVNVSEASQATNQ-GFTRN-SSSVSPHGYVPSTTPQQTNYNS-VTTSMNGYGN-
AGMSNLG--GSPTFLNGSAANSPYAIVPSSPTMASS---TSLPSNCSSSSGIFSFSPANM
VS-AVKQKSAFAPVVRPQTSPPPTCTSTNGN-------SLQDQS----------FVDSSK
YSTAGSLQGLAFS
>Ebf1_mouse
--------------MFGIQESIQRSGSSMKEEP--LGSGMNAVRTWMQGAGVLDANTAAQ
--SGVGLARAHFEKQPPSNLRKSNFFHFVLALYDRQGQPVEIERTAFVGFVEKEKEANSE
KTNNGIHYRLQLLYSNGIRTEQDFYVRLIDSMTKQAIVYEGQDKNPEMCRVLLTHEIMCS
RCCDKKSCGNRNETPSDPVIIDRFFLKFFLKCNQNCLKNAGNPRDMRRFQVVVSTTVNVD
GHVLAVSDNMFVHNNSKHGRRARRLDPSEA----------------------------AT
PCIKAISPSEGWTTGGATVIIIGDNFFDGLQVIFGTMLVWSELITPHAIRVQTPPRHIPG
VVEVTLSYKSKQFCKGTPGRFIYT-ALNEPTIDYGFQRLQKVIPRHPGDPERLPKEVILK
RAADLVEALYGMPHNNQEIILKRAADIAEALYSVPRNHNQLPALAN---TSVHAGMMGVN
SFSGQLAVNVSEASQATNQ-GFTRN-SSSVSPHGYVPSTTPQQTNYNS-VTTSMNGYGS-
AAMSNLG--GSPTFLNGSAANSPYAIVPSSPTMASS---TSLPSNCSSSSGIFSFSPANM
VS-AVKQKSAFAPVVRPQTSPPPTCTSTNGN-------SLQAIS---------GMIVPPM
-------------
>Ebf1_human
--------------MFGIQESIQRSGSSMKEEP--LGSGMNAVRTWMQGAGVLDANTAAQ
--SGVGLARAHFEKQPPSNLRKSNFFHFVLALYDRQGQPVEIERTAFVGFVEKEKEANSE
KTNNGIHYRLQLLYSNGIRTEQDFYVRLIDSMTKQAIVYEGQDKNPEMCRVLLTHEIMCS
RCCDKKSCGNRNETPSDPVIIDRFFLKFFLKCNQNCLKNAGNPRDMRRFQVVVSTTVNVD
GHVLAVSDNMFVHNNSKHGRRARRLDPSEGT--------------------PSYLEH-AT
PCIKAISPSEGWTTGGATVIIIGDNFFDGLQVIFGTMLVWSELITPHAIRVQTPPRHIPG
VVEVTLSYKSKQFCKGTPGRFIYT-ALNEPTIDYGFQRLQKVIPRHPGDPERLPKEVILK
RAADLVEALYGMPHNNQEIILKRAADIAEALYSVPRNHNQLPALAN---TSVHAGMMGVN
SFSGQLAVNVSEASQATNQ-GFTRN-SSSVSPHGYVPSTTPQQTNYNS-VTTSMNGYGS-
AAMSNLG--GSPTFLNGSAANSPYAIVPSSPTMASS---TSLPSNCSSSSGIFSFSPANM
VS-AVKQKSAFAPVVRPQTSPPPTCTSTNGN-------SLQAIS---------GMIVPPM
-------------
>Ebf1_chicken
--------------MFGIQESILRSGGSMKEEP--LGAGMNAVRTWMQGAGVLDASTAAQ
--SGVGLARAHFEKQPPSNLRKSNFFHFVLALYDRQGQPVEIERTAFVGFVEKEKEANSE
KTNNGIHYRLQLLYSNGIRTEQDFYVRLIDSMTKQAIVYEGQDKNPEMCRVLLTHEIMCS
RCCDKKSCGNQNETPSDPVIIDRFFLKFFLKCNQNCLKNAGNPRDMTRFQVVVSTTVNVD
GHVLAVSDNMFVHNNSKHGRRARRLDPSEGT--------------------PSYLEH-AT
PCIKAISPSEGWTTGGATVIIIGDNFFDGLQVIFGTMLVWSELITPHAIRVQTPPRHIPG
VVEVTLSYKSKQFCKGTPGRFIYT-ALNEPTIDYGFQRLQKVIPRHPGDPERLPKEVILK
RAADLVEALYGMPHNNQEIILKRAADIAEALYSVPRNHNQLPALAN---TSVHAGMMGVN
SFSGQLAVNVSEASQATSQ-GFTRN-SSSVSPHGYVPSTTPQQTNYNS-VTTSMNGYGN-
AGMSNLG--GSPTFLNGSAANSPYAIVPSSPTMASS---TSLPSNCSSSSGIFSFSPANM
VS-AVKQKSAFAPVVRPQTSPPPTCTSTNGN-------SLQAIS---------GMIVPPM
-------------
>Ebf1_danio
--------------MFGIQDSIQRSGSSMKEEP--IGAGMNAVRTWMQGAGVLDANTAAQ
--SGVGLARAHFEKQPPSNLRKSNFFHFVLALYDRQGQPVEIERTSFVGFVEKEKESTGE
KTNNGIHYRLQLLYSNGIRTEQDFYVRLIDSMTKQAIIYEGQDKNPEMCRVLLTHEIMCS
RCCDKKSCGNRNETPSDPVIIDRFFLKFFLKCNQNCLKNAGNPRDMRRFQVVVSTTVSVD
GHVLAVSDNMFVHNNSKHGRRARRLDPSEGT--------------------PSYLEH-AT
PCIKAISPSEGWTTGGATVIIIGDNFFDGLQVIFGTMLVWSELITPHAIRVQTPPRHIPG
VVEVTLSYKSKQFCKGTPGRFIYT-ALNEPTIDYGFQRLQKVIPRHPGDPERLPKEVILK
RAADLVEALYGMPHNNQEIILKRAADIAEALYNVPRGHNQLPGLTN---SSVHSGMMGVN
SFHSQLAVNVSDSTQAANQ-GFSRN-TSSVSPHGYVPSTTPQQSSYST-VSTSMNGYGN-
AGMTTLG--GSPNFLNGSAANSPYAIVPSSPTMASS---TSLPSNCSSSSGIFSFSPANM
VS-AVKQKSAFAPVVRPQASPPPTCTSAN-----GNGLQVIP-----------GMIVP--
------PM-----
>Ebf3_danio
--------------MFGIQENIPRGGTTMKEEP--LGSGMNPVRSWMHTAGVVDAHTAAQ
--SGVGLARAHYEKQPPSNLRKSNFFHFVLALYDRQGQPVEIERTAFVDFVEKEKEPNSE
KTNNGIHYKLQLLYSNGVRTEQDLYVRLIDSMTKQAIIYEGQDKNPEMCRVLLTHEIMCS
RCCDKKSCGNRNETPSDPVIIDRFFLKFFLKCNQNCLKNAGNPRDMRRFQVVVSTTVNVD
GHVLAVSDNMFVHNNSKHGRRARRLDPSE----------------------------AAT
PCIKAISPSEGWTTGGATVIIIGDNFFDGLQVVFGTMLVWSELITPHAIRVQTPPRHIPG
VVEVTLSYKSKQFCKGAPGRFVYT-ALNEPTIDYGFQRLQKVIPRHPGDPERLPKEVLLK
RAADLVEALYGMPHNNQEIILKRAADIAEALYSVPRNHNQIPSLAN---TASHGGMMGVN
SFSSQLAVNVSETS----QVGYSRN-TSSVSPRGYVPSSTPQQSNYNT-VSNSMNGYGN-
TGMPNLGVPSSPGFLNGSSANSPYG-M-------------KQKSAFAPVVRPQASPPPSC
TSANGNG--------------------------------LQAMS---------GLVVPPM
-------------
>collier_drosophila
MEWGRKLYPSAVSGPRSAGGLMFGLPPTAAVDMNQPRGPMTSLKEEPLGSRWAMQPVVDQ
--SNLGIGRAHFEKQPPSNLRKSNFFHFVIALYDRAGQPIEIERTAFIGFIEKDSESDAT
KTNNGIQYRLQLLYANGARQEQDIFVRLIDSVTKQAIIYEGQDKNPEMCRVLLTHEVMCS
RCCDKKSCGNRNETPSDPVIIDRFFLKFFLKCNQNCLKNAGNPRDMRRFQVVISTQVAVD
GPLLAISDNMFVHNNSKHGRRAKRLDTTEGTGNTSLSISGHPLAPDSTYDGLYPPLPVAT
PCIKAISPSEGWTTGGATVIIVGDNFFDGLQVVFGTMLVWSELITSHAIRVQTPPRHIPG
VVEVTLSYKSKQFCKGSPGRFVYVSALNEPTIDYGFQRLQKLIPRHPGDPEKLQKEIILK
RAADLVEALYSMPRSP-------------------------------------GGSTGFN
SYAGQLAVSVQDGSGQWTEDDYQRAQSSSVSPRGGYCSSASTPHSSGG----SYGATAAS
AAVAATANGYAPAPNMGTLSSSP-G--SVFN----------STSMSAVSSTWHQAFVQHH
HAATAHPHHHYPHPHQPWHNPAVSAATAA-A--------V--------------------
-------------
>Ebf_stongy
--------------MFGIQDSLTRGSTNLKEEP----IITAEVGSAAVRVGWMQPTMVDQ
SASSCGMARAHFEKQPPSNLRKSNFFHFVIALYDRAGQPIEVERTSFVDFIEKEREPDAT
KTNNGIHYRIQMLFHNGVRTEQDLYVRLIDSMTKQAIIYEGQDKNPEMCRVLLTHEIMCS
RCCDKKSCGNRNETPSDPVIIDRFFLKFFLKCNQNCLKNAGNPRDMRRFQVVISTTPNVD
GHVLAMSDNMFVHNNSKHGRRARRLDPSEG----------------------------AT
PCIKAISPSEGWTTGGATVIIVGDNFFDGLQVVFGSMIVWSELITPHAIRVQTPPRHIPG
VVEVTLSYKSKQFCKGAPGRFVYV-SLTEPTIDYGFQRLSKLVPRHPGDPDRLPKVGRRE
GSLEQIVDCFSLPSRMQEIILKRAADLAEALYSMPRNQLPIGPPRSPALNNSSGSLVPMN
SFGSQLEHESDQHS------GWNSS-TSAILLLLCLLPPASQGKSKAASAKVSISSALSR
VIQFQKTGGSIQAFLDNTKETKS-Q--PFLLAIGNSKARITNYMIIVDHKAIPCSARTVE
AAVDLLFKTHFVFGLQYCLSLRQFWTFVQTAIFEIDIG-VSRETPR--------VYVK--
------LDRNC--
</textarea>
<br />
<p>
<input type="submit" value="Submit" />
<input type="button" value="Clear Input Box" onclick="document.getElementById('alignment').value=''" />
</p>
</form>
<div id="footer"><p>BiasViz is an open source project. Download the source code from <a href="http://sourceforge.net/projects/biasviz/">our Sourceforge page</a>.</p>
<a href="http://sourceforge.net/projects/biasviz/"><img src="http://sflogo.sourceforge.net/sflogo.php?group_id=200141&amp;type=2" width="125" height="37" border="0" alt="SourceForge.net Logo" /></a>
</div>
</div>
</body>
</html>

