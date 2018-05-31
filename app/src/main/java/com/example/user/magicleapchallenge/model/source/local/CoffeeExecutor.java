package com.example.user.magicleapchallenge.model.source.local;

import android.os.AsyncTask;

public class CoffeeExecutor extends AsyncTask<CoffeeExecutor.TaskRunner, Integer, Object> {


    TaskRunner taskRunner;

    public  void execute(TaskRunner taskRunner) {

        this.taskRunner = taskRunner;
        CoffeeExecutor coffeeExecutor = new CoffeeExecutor();
        coffeeExecutor.execute();

    }

    @Override
    protected Object doInBackground(TaskRunner... taskRunners) {
        taskRunners[0].runOnWorker();

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

    }

    interface TaskRunner {
        void runOnWorker();

        void onSuccess(Object... objects);

        void onFailure(String error);


    }

}
