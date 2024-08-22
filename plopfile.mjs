const moduleMapping = {
    framework: {
        name: 'system',
        package: 'com.leuan.lepton.framework',
        menuId: 1
    },
    customer: {
        name: 'customer',
        package: 'com.leuan.lepton.customer',
        menuId: 2
    }
}
export default function Plopfile(plop) {
    plop.setGenerator('pojo', {
            description: 'application controller logic',
            prompts: [
                {
                    type: 'list',
                    name: 'moduleName',
                    message: '模块',
                    choices: Object.keys(moduleMapping)
                },
                {
                    type: 'input',
                    name: 'biz',
                    message: '请输入业务名称(例如:user)'
                },
                {
                    type: 'input',
                    name: 'comment',
                    message: '请输入业务注释'
                }
            ],
            actions: (data) => {
                console.log(`plop ${data.module} ${data.biz} ${data.comment}`)
                data.module = moduleMapping[data.moduleName]
                return [
                    {
                        type: 'addMany',
                        base: '_templates/service',
                        destination: 'src/main/kotlin/com/leuan/lepton/{{lowerCase moduleName}}',
                        force: true,
                        templateFiles: '_templates/service',
                        data: {
                            basePackage: 'com.leuan.lepton',
                        }
                    },
                    {
                        type: 'append',
                        templateFile: '_templates/permission.sql.hbs',
                        unique: false,
                        path: 'gen/permission.sql'
                    },
                    {
                        type: 'append',
                        templateFile: '_templates/gen.log.hbs',
                        unique: false,
                        path: 'gen/gen.log'
                    },
                ]
            }
        }
    )
}