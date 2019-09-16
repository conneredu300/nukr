# nukr

This is an api created to manage friendlist and connect people

## Usage

lein run => starts the webserver and generates => 127.0.0.1:3000

1. 	Shows welcome
	127.0.0.1:3000/

2.  show anybody created
	127.0.0.1:3000/people

3. 	Create new person
	127.0.0.1:3000/add?firstname="something"&lastname="somethingtoo"

4. 	Finds someone by id
	127.0.0.1:3000/find?id=someid

5. 	Mark two people as friends
	127.0.0.1:3000/connect?id=someid&idfriend=anotherid

6. 	Returns a list of friend suggests for someone by this guyid
	127.0.0.1:3000/suggestions?id=someid

7. 	This only toggle person status to active or inactive
	127.0.0.1:3000/update?id=someid

For runing tests it's preferable use:

1.	lein repl
2.	(require '[clojure.test :refer [run-tests]])
3.	(require 'nukr.core-test)
4.	(run-tests 'nukr.core-test)

## License

Copyright © 2019 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
