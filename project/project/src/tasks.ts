import api from '../api';  

// Типы для задач 
interface Task {
  id: string;
  title: string;
  description?: string;
  status: 'todo' | 'in_progress' | 'done';
  priority: 'low' | 'medium' | 'high';
  projectId: string;
}

interface TaskCreateDto {
  title: string;
  description?: string;
  status?: 'todo' | 'in_progress' | 'done';
  priority?: 'low' | 'medium' | 'high';
}

interface TaskFilters {
  status?: 'todo' | 'in_progress' | 'done';
  priority?: 'low' | 'medium' | 'high';
}

// Запросы к бэкенду
export const fetchTasks = async (
  projectId: string,
  filters?: TaskFilters
): Promise<Task[]> => {
  const response = await api.get<Task[]>(`/projects/${projectId}/tasks`, {
    params: filters,
  });
  return response.data;
};

export const createTask = async (
  projectId: string,
  taskData: TaskCreateDto
): Promise<Task> => {
  const response = await api.post<Task>(
    `/projects/${projectId}/tasks`,
    taskData
  );
  return response.data;
};