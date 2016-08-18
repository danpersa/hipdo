(ns hipdo.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [hipdo.core-test]))

(doo-tests 'hipdo.core-test)
