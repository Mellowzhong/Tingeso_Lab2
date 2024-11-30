import api from "../../Utils/BaseUrl";

export const getSimulation = async (simulationData) => {
  try {
    const response = await api.post("/utils/simulation", simulationData, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    return response.data;
  } catch (error) {
    console.error("Error getting simulation", error);
  }
};

export const getTotalCost = async () => {
  try {
    const response = await api.get("/utils/totalCost");
    return response.data;
  } catch (error) {
    console.error("Error getting total cost", error);
  }
};
