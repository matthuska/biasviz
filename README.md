# BiasViz: Protein Amino Acid Bias Analysis Applet

BiasViz is a small Java applet that can be used to visualize multiple sequence
alignments and look for amino acid bias.

Homepage: http://matthuska.github.io/biasviz/

Development: http://github.com/matthuska/biasviz

## Setup and running

To run BiasViz you will need to first retrieve the source code. One way to do
this is to clone the repository using git:

	$ git clone https://github.com/matthuska/biasviz.git
	$ cd biasviz

BiasViz requires Apache Ant and Java 1.5 or greater. If you happen to use
[conda](https://docs.conda.io), you can easily install these by creating the
provided conda environment:

	$ conda env create -f environment.yml
	$ conda activate biasviz

Then, to build and start BiasViz, you run the following from the biasviz
directory:

	$ ant run

This should build BiasViz, and pop up a window with the application in it.

There are are other options for building BiasViz, you can see these by running
`ant -p`.
