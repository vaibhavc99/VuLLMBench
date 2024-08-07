[![data under CC BY 4.0 license](https://img.shields.io/badge/data%20license-CC%20BY%204.0-green)](https://creativecommons.org/licenses/by/4.0/)
<a href="https://doi.org/10.5281/zenodo.4476563">
  <img align="right" src="https://zenodo.org/badge/DOI/10.5281/zenodo.4476563.svg" alt="DOI: 10.5281/zenodo.4476563">
 </a>
 
# _CVEfixes_ Dataset v1.0.7: Automatically Collected Vulnerabilities and Their Fixes from Open-Source Software (Aug 2022)

_CVEfixes_ is a comprehensive vulnerability dataset that is
automatically collected and curated from Common Vulnerabilities
and Exposures (CVE) records in the public
[U.S. National Vulnerability Database (NVD)](https://nvd.nist.gov/).
The goal is to support data-driven security research based on source
code and source code metrics related to fixes for CVEs in the NVD by
providing detailed information on different interlinked levels of
abstraction, such as the commit-, file-, and method level, as well
as the repository- and CVE level.

This release, v1.0.7, covers all published CVEs up to 27 August 2022.
All open-source projects that were reported in CVE records
in the NVD in this time frame _and_ had publicly available git
repositories were fetched and considered for the construction of this
vulnerability dataset. The dataset is organized as a relational
database and covers 7798 vulnerability fixing commits in 2487 open
source projects for a total of 7637 CVEs in 209 different Common
Weakness Enumeration (CWE) types. The dataset includes the source
code before and after changing 29309 files and 98250 functions.

This repository includes the SQL dump of the dataset, as well as the
JSON for the CVEs, and the XML of the CWEs at the time of collection.
The complete process has been documented in the paper
_"CVEfixes: Automated Collection of Vulnerabilities and Their Fixes
from Open-Source Software"_, that is published in the Proceedings of
the 17th International Conference on Predictive Models and Data
Analytics in Software Engineering (PROMISE '21).
You will find a copy of the paper in the Doc folder. Instructions
for using _CVEfixes_ are in [INSTALL.md](INSTALL.md).


## Citation and Zenodo links

The dataset has been registered at Zenodo with DOI:
[10.5281/zenodo.4476563](https://doi.org/10.5281/zenodo.4476563). The
GitHub repository containing the code to automatically collect the
dataset can be found at <https://github.com/secureIT-project/CVEfixes>,
released on Zenodo with DOI:
[10.5281/zenodo.5111494](https://doi.org/10.5281/zenodo.5111494).

Please cite this work by referring to the paper: 
> Guru Bhandari, Amara Naseer, and Leon Moonen. 2021. CVEfixes:
> Automated Collection of Vulnerabilities and Their Fixes from
> Open-Source Software. In Proceedings of the 17th International
> Conference on Predictive Models and Data Analytics in Software
> Engineering (PROMISE '21). ACM, 10 pages.
> <https://doi.org/10.1145/3475960.3475985>


    @inproceedings{bhandari2021:cvefixes,
        title = {{CVEfixes: Automated Collection of Vulnerabilities  and Their Fixes from Open-Source Software}},
        booktitle = {{Proceedings of the 17th International Conference on Predictive Models and Data Analytics in Software Engineering (PROMISE '21)}},
        author = {Bhandari, Guru and Naseer, Amara and Moonen, Leon},
        year = {2021},
        pages = {10},
        publisher = {{ACM}},
        doi = {10.1145/3475960.3475985},
        copyright = {Open Access},
        isbn = {978-1-4503-8680-7},
        language = {en}
    }


## Acknowledgement

This work has been financially supported by the Research Council of
Norway through the secureIT project (RCN contract \#288787).
