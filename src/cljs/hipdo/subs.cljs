(ns hipdo.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/register-sub
 :name
 (fn [db]
   (reaction (:name @db))))

(re-frame/register-sub
 :active-panel
 (fn [db _]
   (reaction (:active-panel @db))))

(re-frame/register-sub
  :left-menu-visible?
  (fn [db _]
    (reaction (:left-menu-visible? @db))))

(re-frame/register-sub
  :todos
  (fn [db _]
    (reaction (:todos @db))))