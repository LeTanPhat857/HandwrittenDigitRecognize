package controller;

public interface iController {

	public void startTraining() throws Exception;

	public void startPredicting();

	public void startSaving(String savingPath);

	public void startLoading(String loadingPath);

	public void startTesting();
}
