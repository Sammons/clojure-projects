(ns tutorial-client.start
  (:require [io.pedestal.app.protocols :as p]
            [io.pedestal.app :as app]
            [io.pedestal.app.render.push :as push-render]
            [io.pedestal.app.render :as render]
            [io.pedestal.app.messages :as msg]
            [tutorial-client.behavior :as behavior]
            [tutorial-client.rendering :as rendering]
            [tutorial-client.post-processing :as post]))

(defn create-app [render-config]
  (let [app (app/build (post/add-post-processors behavior/example-app))
        render-fn (push-render/renderer "content" render-config render/log-fn)
        app-model (render/consume-app-model app render-fn)]
    (app/begin app)
    {:app app :app-model app-model}))

(defn ^:export main []
  (create-app (rendering/render-config)))
